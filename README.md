# DataBindingBase
![MacDown logo](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494229063511&di=b4c2b601c2648f46295b1c14cfb3ba32&imgtype=0&src=http%3A%2F%2Fwww.7dapei.com%2Fimages%2F201408a%2F939.6.jpg)

## XML绑定
	
	<layout xmlns:android="http://schemas.android.com/apk/res/android"
    	xmlns:bind="http://schemas.android.com/apk/res-auto"
    	xmlns:tools="http://schemas.android.com/tools">
		 <data>
	        <variable
	            name="person"
	            type="com.jiangwei.databindingbase.Person" />
	
	        <variable
	            name="handler"
	            type="com.jiangwei.databindingbase.MainActivity.Handler" />
	
	        <variable
	            name="time"
	            type="java.util.Date" />
	    </data>
	    
	     <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:text="@{person.nation}" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:text="@{person.sex}" />
	    ...
    </layout>
    
* 在Activity中初始化

		activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        person = new Person("jiangwei", "China", "男");
        activityMainBinding.setVariable(BR.person, person);
        activityMainBinding.setVariable(BR.handler, new Handler());
        activityMainBinding.setTime(new Date());
    
## 绑定属性
		
		 <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:text="@{person.sex}" />
## 绑定事件方法

		<Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:onClick="@{handler::onClick}"
                android:text="onClick" />
               
## 双向绑定

		<EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:hint="双向绑定"
                android:text="@={person.nation}" />
                
## 回调方法

		<Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:onClick="@{() -> handler.onClickedListener(person)}"
                android:text="回调" />
                
## include布局参数传递
		<include
                layout="@layout/include_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                bind:person="@{person}" />
                
## ViewStub布局参数传递
		<ViewStub
                android:id="@+id/view_stub"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginTop="20dp"
                android:layout="@layout/view_stub"
                bind:person="@{person}" />
           
* 在Activity中初始化ViewStub，ViewStub不会一开始不会初始化，需要在Activity手动加载。

	  	activityMainBinding.viewStub.getViewStub().inflate();
	  	
## BindingAdapter

	   <ImageView
	                android:layout_width="100dp"
	                android:layout_height="100dp"
	                bind:bindUri="@{`http://avatar.csdn.net/4/9/8/1_a10615.jpg`}"
	                bind:placeHolder="@{@drawable/ic_launcher}" />
	                
在Utils类中通过注解的方式找到这两个自定义属性，属性名字按照顺序写：

	@BindingAdapter({ "bind:bindUri", "bind:placeHolder" })
	    public static void loadImageFromUrl(ImageView iv, String bindUri, Drawable placeHolder) {
	        Glide.with(iv.getContext()).load(bindUri).placeholder(placeHolder).into(iv);
	    }
	    
## BindingConversion
time是Java.Utils.Date类型的。无法转换成String类型，因此需要BindingConversion转换。

			<TextView
		          android:text="@{time}"
		          android:layout_centerHorizontal="true"
		          android:layout_width="wrap_content"
		          android:layout_height="match_parent" />
		          
Utils中通过注解将date转换成String类型的时间
		          
	@BindingConversion()
    public static String convertTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    
同理：    
    
		<TextView
		        android:layout_width="wrap_content"
		        android:layout_height="wrap_content"
		        android:layout_centerHorizontal="true"
		        android:text="@{person}" />
		        
Utils中

	   @BindingConversion()
	   public static String convertPerson(Person person) {
	        Person p =new Person("zhouwenkai", "china", "man");
	        return p.name;
	    }
	    
## DataBinding+RecycleView

### 定义ViewHolder
定义ViewHolder传入泛型T，存入绑定的databinding

		
	public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
	    private T mBinding;
	
	    public BindingViewHolder(T binding) {
	        super(binding.getRoot());
	        mBinding = binding;
	    }
	
	    public T getBinding() {
	        return mBinding;
	    }
	}
	
### Adapter根据ViewType创建View

	@Override
    public int getItemViewType(int position) {
        if (mLists.get(position).name.equals("姜威")) {
            return PERSON_TYPE_ONE;
        } else {
            return PERSON_TYPE_TWO;
        }
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        if (viewType == PERSON_TYPE_ONE) {
            binding = DataBindingUtil.inflate(mInflater, R.layout.item_one, parent, false);
        } else {
            binding = DataBindingUtil.inflate(mInflater, R.layout.item_two, parent, false);
        }
        return new BindingViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Person person = mLists.get(position);
        holder.getBinding().setVariable(BR.person, person);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(person);
                }
            }
        });
    }

                
                
                
                
                
                
                
                
                
                
                
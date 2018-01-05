## 项目说明
    此库是一个性能测量工具，跟踪某个过程中各个步骤的耗时。可打印出整个过程的总耗时，过程中每个步骤的耗时，以及每个步骤耗时占总耗时的百分比。
## 使用说明
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Profiler profiler = Profilers.startup();
        // 添加枝步骤，并进入子分支.
        profiler.into("MainActivity");
        profiler.into("onCreate");
        super.onCreate(savedInstanceState);
        // 添加叶子步骤.
        profiler.split("superOnCreate");
        profiler.split("setActivityCreate");
        setContentView(R.layout.activity_main);
        profiler.split("setContentView");
        profiler.dump();
        // 退回上级分支.
        profiler.out();
    }
## 使用方法
    allprojects {
		repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		  }
	  }
  
    dependencies {
            compile 'com.github.ZLOVE320483:Profiler:v1.0'
    }
## 打印结果展示
    startupProfiler: 54, 100%
    MainActivity: 0
      onCreate: 54, 100%
          setActivityCreate: 0
          setContentView: 48, 88%


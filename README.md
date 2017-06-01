# SearchView
SearchView

Usage

[![](https://jitpack.io/v/xjh1994/SearchView.svg)](https://jitpack.io/#xjh1994/SearchView)

To get **HelloAndroid** into your build:

**Step 1**. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
**Step 2**. Add the dependency  [latest_release](https://github.com/xjh1994/SearchView/releases)

	dependencies {
	        compile 'com.github.xjh1994:SearchView:1.0.2'
	}


![截图](images/screenshots.jpg)

### XML

``` xml
<com.xjh1994.searchview.SearchView
    android:id="@+id/searchView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@android:color/holo_green_dark"
    android:padding="10dp" />
```

### Code

``` java

searchView.addOnSearchTextChangedListener(new SearchView.OnSearchTextChangedListener() {
    @Override
    public void onSearchTextChanged(String searchText) {
        // 历史记录。如果有，展示；如果没有，显示原文字

        if (historyFragment == null) {
            historyFragment = new HistoryFragment();
            FragmentUtils.addFragment(getSupportFragmentManager(), historyFragment, R.id.flContent);
        } else {
            FragmentUtils.showFragment(historyFragment);
            historyFragment.refresh(searchText);
        }
        if (resultFragment != null) {
            FragmentUtils.hideFragment(resultFragment);
        }
    }

    @Override
    public void onSearchTextEmpty() {
        if (historyFragment != null) {
            FragmentUtils.hideFragment(historyFragment);
        }
        if (resultFragment != null) {
            FragmentUtils.hideFragment(resultFragment);
        }
    }
});

searchView.addOnSearchClickListener(new SearchView.OnSearchClickListener() {
    @Override
    public void onSearchClick(String trim) {
        // 查询并显示搜索结果

        if (resultFragment == null) {
            resultFragment = new ResultFragment();
            FragmentUtils.addFragment(getSupportFragmentManager(), resultFragment, R.id.flContent);
        } else {
            FragmentUtils.showFragment(resultFragment);
            resultFragment.refresh(trim);
        }
        if (historyFragment != null) {
            FragmentUtils.hideFragment(historyFragment);
        }
    }

    @Override
    public void onSearchInputEmpty() {
        // 输入为空
        Toast.makeText(MainActivity.this, "empty", Toast.LENGTH_SHORT).show();
    }
});

searchView.setOnCancelClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
    }
});
```

### attrs

``` xml
<attr name="searchBackgroundDrawable" format="reference" />
<attr name="searchIcon" format="reference" />
<attr name="searchClearIcon" format="reference" />
<attr name="searchCancelIcon" format="reference" />
<attr name="searchCancelText" format="string|reference" />
<attr name="searchHintText" format="string|reference" />
<attr name="searchLoadingEnabled" format="boolean" />
<attr name="searchEmptyToast" format="string|reference" />
```
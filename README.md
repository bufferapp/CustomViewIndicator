# Custom View Indicator
An Android ViewPager indicator that allows you to provide custom views for display. We used this during a new feature we built, figured it might be useful for someone else along the line!

<p align="center">
    <img src ="demo.gif" />
</p>

## Usage

Define the `CustomViewPageIndicator` in your layout file:

    <org.buffer.android.customviewindicator.CustomViewPageIndicator
        android:id="@+id/view_indicator"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        app:selectedItem="0"
        app:itemRightMargin="8dp"
        app:itemleftMargin="8dp" />
        
Assign a list of views to the `CustomViewPageIndicator`

    pageIndicator.indicatorViews = listOf(customViews)
    
Attach the `CustomViewPageIndicator` to a ViewPager instance:

    pageIndicator.attachToViewPager(viewPager)

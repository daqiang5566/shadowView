# shadowView
/***
 * shadowRadius ：
 *   (1)支持各种颜色阴影,颜色必须带有透明度,否则阴影不准确; eg:"#1a3e4043"
 *   (2)当背景使用,阴影半径会占用原来布局的一部分空间，空间大小等于 shadowRadius
 *      如果 dx,dy为0，上下左右的阴影的高度等于shadowRadius,
 *      如果shadowRadius>dy>0 阴影向下倾斜,那么顶部的阴影高度等于：shadowRadius - dx,如果dy>= shadowRadius,那么上面的阴影高度等于0,下面阴影半径为 shadowRadius + dx;
 *      其他情况同理,方便在写布局的时候计算上下左右边距
 *   (3)支持4个方向是否设置阴影,ALL,LEFT,TOP,RIGHT,BOTTOM
 *   (4)支持圆角
 *
 *   eg:
 *        <com.custom.shadowview.ShadowView
             android:layout_centerInParent="true"
             android:layout_width="200dp"
             android:layout_height="100dp"
             app:shadowSide="all"
             app:shadowColor="#1aff9c17"
             app:roundRadius="4dp"
             app:shadowDx="0dp"
             app:shadowDy="5dp"
             app:shadowRadius="4dp"/>
 *
 *   @author liqiang10


     (5) 远程依赖
        (1)
        	allprojects {
        		repositories {
        			...
        			maven { url 'https://jitpack.io' }
        		}
        	}
        (2)
            dependencies {
                    implementation 'com.github.daqiang5566:shadowView:Tag'
            }

 * **/

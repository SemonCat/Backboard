package com.tumblr.backboard.performer;

import android.support.annotation.NonNull;
import android.util.Property;
import android.view.View;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Maps the motion of a {@link com.facebook.rebound.Spring} to a
 * {@link android.util.Property} on a {@link android.view.View}.
 * <p/>
 * Created by ericleong on 5/6/14.
 */
public class MapPerformer extends Performer {

	private static final String TAG = MapPerformer.class.getSimpleName();

	private float initialStart, initialEnd, start, end;

	/**
	 * Constructor. Maps the spring domain [0, 1] to the view property range [0, 1].
	 *
	 * @param target
	 * 		the view to modify.
	 * @param property
	 * 		the view property to modify.
	 */
	public MapPerformer(@NonNull View target, @NonNull Property<View, Float> property) {
		this(target, property, 0, 1, 0, 1);
	}

	/**
	 * Constructor. Maps the spring domain [0, 1] to the specified view property range.
	 *
	 * @param target
	 * 		the view to modify.
	 * @param property
	 * 		the view property to modify.
	 * @param start
	 * 		the minimum value for the view property range.
	 * @param end
	 * 		the maximum value for the view property range.
	 */
	public MapPerformer(@NonNull View target, @NonNull Property<View, Float> property, float start, float end) {
		this(target, property, 0, 1, start, end);
	}

	/**
	 * Constructor. Maps the spring domain [0, 1] to the specified view property range.
	 *
	 * @param target
	 * 		the view to modify.
	 * @param property
	 * 		the view property to modify.
	 * @param initialStart
	 * 		the minimum value for the spring domain.
	 * @param initialEnd
	 * 		the maximum value for the spring domain.
	 * @param start
	 * 		the minimum value for the view property range.
	 * @param end
	 * 		the maximum value for the view property range.
	 */
	public MapPerformer(@NonNull View target, @NonNull Property<View, Float> property, float initialStart,
	                    float initialEnd, float start, float end) {
		super(target, property);

		this.initialStart = initialStart;
		this.initialEnd = initialEnd;
		this.start = start;
		this.end = end;
	}

	@Override
	public void onSpringUpdate(@NonNull Spring spring) {

		mProperty.set(mTarget,
				(float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(),
						initialStart, initialEnd, start, end)
		);
	}
}

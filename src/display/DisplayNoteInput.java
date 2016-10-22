package display;

public class DisplayNoteInput {

	private int upDirection, upTime, upIndex;
	private int downDirection, downTime, downIndex;
	private int leftDirection, leftTime, leftIndex;
	private int rightDirection, rightTime, rightIndex;
	private int maxIndex;
	private int changeTime;

	public DisplayNoteInput(int changeTime, int maxIndex) {
		this.upDirection = -1;
		this.downDirection = -1;
		this.leftDirection = -1;
		this.rightDirection = -1;
		this.upTime = 0;
		this.downTime = 0;
		this.leftTime = 0;
		this.rightTime = 0;
		this.upIndex = 0;
		this.downIndex = 0;
		this.leftIndex = 0;
		this.rightIndex = 0;
		this.changeTime = changeTime;
		this.maxIndex = maxIndex;
	}

	/**
	 * Updates the images for each direction based on the current time by
	 * calculating how much time has passed since each direction was last
	 * pressed. If this method is not ever called, the arrow images will never
	 * change.
	 * 
	 * @param current the current time, in milliseconds.
	 */
	public void update(int current) {
		if (current > upTime) {
			if (upDirection == -1) {
				upTime = current + changeTime;
				upIndex = upIndex + 1 > maxIndex ? maxIndex : upIndex + 1;
			} else if (upDirection == 1) {
				upTime = current + changeTime;
				upIndex = upIndex - 1 < 0 ? 0 : upIndex - 1;
			}
		}
		if (current > downTime) {
			if (downDirection == -1) {
				downTime = current + changeTime;
				downIndex = downIndex + 1 > maxIndex ? maxIndex : downIndex + 1;
			} else if (downDirection == 1) {
				downTime = current + changeTime;
				downIndex = downIndex - 1 < 0 ? 0 : downIndex - 1;
			}
		}
		if (current > rightTime) {
			if (rightDirection == -1) {
				rightTime = current + changeTime;
				rightIndex = rightIndex + 1 > maxIndex ? maxIndex
						: rightIndex + 1;
			} else if (rightDirection == 1) {
				rightTime = current + changeTime;
				rightIndex = rightIndex - 1 < 0 ? 0 : rightIndex - 1;
			}
		}
		if (current > leftTime) {
			if (leftDirection == -1) {
				leftTime = current + changeTime;
				leftIndex = leftIndex + 1 > maxIndex ? maxIndex : leftIndex + 1;
			} else if (leftDirection == 1) {
				leftTime = current + changeTime;
				leftIndex = leftIndex - 1 < 0 ? 0 : leftIndex - 1;
			}
		}
	}

	public int getLeft() {
		return leftIndex + 1 % maxIndex;
	}

	public int getUp() {
		return upIndex + 1 % maxIndex;
	}

	public int getDown() {
		return downIndex + 1 % maxIndex;
	}

	public int getRight() {
		return rightIndex + 1 % maxIndex;
	}

	public void pressUp() {
		this.upDirection = 1;
	}

	public void pressDown() {
		this.downDirection = 1;
	}

	public void pressLeft() {
		this.leftDirection = 1;
	}

	public void pressRight() {
		this.rightDirection = 1;
	}

	public void releaseUp() {
		this.upDirection = -1;
	}

	public void releaseDown() {
		this.downDirection = -1;
	}

	public void releaseLeft() {
		this.leftDirection = -1;
	}

	public void releaseRight() {
		this.rightDirection = -1;
	}

}

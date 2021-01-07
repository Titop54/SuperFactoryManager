/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package ca.teamdman.sfm.client.gui.flow.core;

import ca.teamdman.sfm.common.flow.core.Position;

public class Size {

	private int width, height;

	public Size(Size copy) {
		this(copy.getWidth(), copy.getHeight());
	}

	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Size copy() {
		return new Size(this);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setSize(Size other) {
		this.width = other.getWidth();
		this.height = other.getHeight();
	}

	/**
	 * Checks if a coordinate is contained in this element
	 *
	 * @param x Scaled x coordinate
	 * @param y Scaled y coordinate
	 * @return true if coordinate is contained in this element's area, false otherwise
	 */
	public boolean contains(Position myPosition, int x, int y) {
		return x >= myPosition.getX() && x <= myPosition.getX() + getWidth() && y >= myPosition.getY()
			&& y <= myPosition.getY() + getHeight();
	}

	/**
	 * Create a new Size object that will match this width, but with a custom height
	 * @param height Custom constant height
	 * @return Size delegate
	 */
	public Size withConstantHeight(int height) {
		return new Size(0,0) {
			@Override
			public int getWidth() {
				return Size.this.getWidth();
			}

			@Override
			public int getHeight() {
				return height;
			}
		};
	}

	/**
	 * Create a new Size object that will match this height, but with a custom width
	 * @param width Custom constant width
	 * @return Size delegate
	 */
	public Size withConstantWidth(int width) {
		return new Size(0,0) {
			@Override
			public int getWidth() {
				return width;
			}

			@Override
			public int getHeight() {
				return Size.this.getHeight();
			}
		};
	}

	@Override
	public String toString() {
		return "Size[" + getWidth() + ", " + getHeight() + ']';
	}
}

/**
 * 
 */
package com.basiscomponents.ui.layout;

import java.awt.Dimension;

import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.PlatformDefaults;

import com.basis.bbj.client.util.BBjException;
import com.basis.bbj.proxies.BBjSysGui;
import com.basis.util.common.BasisNumber;

/**
 * @author rlance
 * 
 */
public class BBComponentWrapper implements ComponentWrapper {

	private BBComponent component;
	private BBContainerWrapper containerWrapper;

	public BBComponentWrapper(BBComponent component) {
		this.component = component;
	}

	@Override
	public Object getComponent() {
		return this.component;
	}

	@Override
	public int getX() {
		try {
			return this.component.getControl().getX().intValue();
		} catch (BBjException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getY() {
		try {
			return this.component.getControl().getY().intValue();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getWidth() {
		try {
			return this.component.getControl().getWidth().intValue();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getHeight() {
		try {
			return this.component.getControl().getHeight().intValue();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getScreenLocationX() {
		try {
			return this.component.getControl().getScreenX();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getScreenLocationY() {
		try {
			return this.component.getControl().getScreenY();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getMinimumWidth(int hHint) {
		return this.component.getMinimumSize().width;
	}

	@Override
	public int getMinimumHeight(int wHint) {
		return this.component.getMinimumSize().height;
	}

	@Override
	public int getPreferredWidth(int hHint) {
		return this.component.getPreferredSize().width;
	}

	@Override
	public int getPreferredHeight(int wHint) {
		return this.component.getPreferredSize().height;
	}

	@Override
	public int getMaximumWidth(int hHint) {
		return this.component.getMaximumSize().width;
	}

	@Override
	public int getMaximumHeight(int wHint) {
		return this.component.getMaximumSize().height;
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		BasisNumber bx = BasisNumber.createBasisNumber(x);
		BasisNumber by = BasisNumber.createBasisNumber(y);
		BasisNumber bw = BasisNumber.createBasisNumber(width);
		BasisNumber bh = BasisNumber.createBasisNumber(height);
		try {
			this.component.getControl().setLocation(bx, by);
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.component.getControl().setSize(bw, bh);
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isVisible() {
		try {
			return this.component.getControl().isVisible();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getBaseline(int width, int height) {
		// TODO
		return -1;
	}

	@Override
	public boolean hasBaseline() {
		// TODO
		return false;
	}

	@Override
	public ContainerWrapper getParent() {
		return this.containerWrapper;
	}

	@Override
	public float getPixelUnitFactor(boolean isHor) {
		// TODO Auto-generated method stub
		return 1.0f;
	}

	@Override
	public int getHorizontalScreenDPI() {
		// TODO Auto-generated method stub
		return PlatformDefaults.getDefaultDPI();
	}

	@Override
	public int getVerticalScreenDPI() {
		// TODO Auto-generated method stub
		return PlatformDefaults.getDefaultDPI();
	}

	@Override
	public int getScreenWidth() {
		// TODO Auto-generated method stub
		// BBjAPI().getSysGui().getSystemMetrics().getScreenSize().width
		return 1024;
	}

	@Override
	public int getScreenHeight() {
		// TODO Auto-generated method stub
		// BBjAPI().getSysGui().getSystemMetrics().getScreenSize().height
		return 768;
	}

	@Override
	public String getLinkId() {
		try {
			if (this.component.getControl().getName().length() > 0) {
				return this.component.getControl().getName();
			} else {
				return null;
			}
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getLayoutHashCode() {

		Dimension d = this.component.getMaximumSize();
		int hash = d.width + (d.height << 5);
		d = this.component.getPreferredSize();
		hash += (d.width << 10) + (d.height << 15);
		d = this.component.getMinimumSize();
		hash += (d.width << 20) + (d.height << 25);

		if (isVisible())
			hash += 1324511;

		String id = getLinkId();
		if (id != null)
			hash += id.hashCode();

		return hash;
	}

	@Override
	public int[] getVisualPadding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getComponentType(boolean disregardScrollPane) {

		int t;
		try {
			t = this.component.getControl().getControlType();
		} catch (BBjException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t = -1;
		}

		if (t == BBjSysGui.EDIT_TYPE || t == BBjSysGui.INPUTD_TYPE
				|| t == BBjSysGui.INPUTE_TYPE || t == BBjSysGui.INPUTN_TYPE) {
			return TYPE_TEXT_FIELD;
		} else if (t == BBjSysGui.STATIC_TEXT_TYPE) {
			return TYPE_LABEL;
		} else if (t == BBjSysGui.CHECKBOX_TYPE) {
			return TYPE_CHECK_BOX;
		} else if (t == BBjSysGui.BUTTON_TYPE || t == BBjSysGui.TOOLBUTTON_TYPE) {
			return TYPE_BUTTON;
		} else if (t == BBjSysGui.LISTBUTTON_TYPE
				|| t == BBjSysGui.LISTEDIT_TYPE
				|| t == BBjSysGui.RADIOBUTTON_TYPE) {
			return TYPE_LABEL;
		} else if (t == BBjSysGui.CEDIT_TYPE) {
			return TYPE_TEXT_AREA;
		} else if (t == BBjSysGui.IMAGE_TYPE) {
			return TYPE_PANEL;
		} else if (t == BBjSysGui.LISTBOX_TYPE) {
			return TYPE_LIST;
		} else if (t == BBjSysGui.DA_GRID_TYPE || t == BBjSysGui.DB_GRID_TYPE
				|| t == BBjSysGui.GRID_TYPE) {
			return TYPE_TABLE;
		} else if (t == BBjSysGui.EDIT_SPINNER_TYPE
				|| t == BBjSysGui.INPUTD_SPINNER_TYPE
				|| t == BBjSysGui.INPUTE_SPINNER_TYPE
				|| t == BBjSysGui.INPUTN_SPINNER_TYPE) {
			return TYPE_SPINNER;
		} else if (t == BBjSysGui.PROGRESS_TYPE) {
			return TYPE_PROGRESS_BAR;
		} else if (t == BBjSysGui.HSLIDER_TYPE || t == BBjSysGui.NAVIGATOR_TYPE
				|| t == BBjSysGui.VSLIDER_TYPE) {
			return TYPE_SLIDER;
		} else if (t == BBjSysGui.HSCROLL_TYPE || t == BBjSysGui.VSCROLL_TYPE) {
			return TYPE_SCROLL_BAR;
		} else if (t == BBjSysGui.TOP_TYPE || t == BBjSysGui.CHILD_TYPE) {
			return TYPE_CONTAINER;
		} else {
			return TYPE_UNKNOWN;
		}
	}

	/**
	 * @return the containerWrapper
	 */
	public BBContainerWrapper getContainerWrapper() {
		return containerWrapper;
	}

	/**
	 * @param containerWrapper
	 *            the containerWrapper to set
	 */
	public void setContainerWrapper(BBContainerWrapper containerWrapper) {
		this.containerWrapper = containerWrapper;
	}

	public int hashCode() {
		return this.component.hashCode();
	}

	/**
	 * This needs to be overridden so that different wrappers that hold the same
	 * component compare as equal. Otherwise, Grid won't be able to layout the
	 * components correctly.
	 */
	public boolean equals(Object o) {
		if (!(o instanceof BBComponentWrapper)) {
			return false;
		}
		return getComponent().equals(((BBComponentWrapper) o).getComponent());
	}

	@Override
	public void paintDebugOutline(boolean showVisualPadding) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentBias() {
		// Orientation bias = node.getContentBias();
		// return bias == null ? -1 : bias.ordinal(); // 0 ==
		// Orientation.HORIZONTAL and Orientation.HORIZONTAL, 1 =
		// Orientation.VERTICAL and LayoutUtil.VERTICAL
		return 0;
	}

}

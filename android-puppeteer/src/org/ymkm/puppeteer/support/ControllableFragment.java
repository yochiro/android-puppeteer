package org.ymkm.puppeteer.support;

import org.ymkm.puppeteer.core.ControllableFragmentInterface;

public interface ControllableFragment extends ControllableFragmentInterface {

	ControllableFragment sendToUi(Runnable runnable);
}

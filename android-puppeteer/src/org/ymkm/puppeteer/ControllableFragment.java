package org.ymkm.puppeteer;

import org.ymkm.puppeteer.core.ControllableFragmentInterface;

import android.os.Bundle;

public interface ControllableFragment extends ControllableFragmentInterface {

	ControllableFragment sendToUi(Runnable runnable);

    void setArguments(Bundle args);
	Bundle getArguments();
}
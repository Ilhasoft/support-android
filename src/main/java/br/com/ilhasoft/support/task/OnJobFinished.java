package br.com.ilhasoft.support.task;

/**
 * Created by johndalton on 09/12/14.
 */
public interface OnJobFinished<LastParam>  {

    public void onJobFinished(LastParam response);

}

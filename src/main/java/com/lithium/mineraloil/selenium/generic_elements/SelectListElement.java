package com.lithium.mineraloil.selenium.generic_elements;

import lombok.Delegate;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class SelectListElement implements Element, SelectList {
    @Delegate(excludes = {IFrameActions.class})
    private BaseElement baseElement;

    public SelectListElement(By by) {
        baseElement = new BaseElement(by);
    }

    public SelectListElement(By by, int index) {
        baseElement = new BaseElement(by, index);
    }

    public SelectListElement(Element parentElement, By by) {
        baseElement = new BaseElement(parentElement, by);
    }

    public SelectListElement(Element parentElement, By by, int index) {
        baseElement = new BaseElement(parentElement, by, index);
    }

    @Override
    public SelectListElement registerIFrame(Element iframeElement) {
        baseElement.registerIFrame(iframeElement);
        return this;
    }

    public String getSelectedOption() {
        return new Select(baseElement.locateElement()).getFirstSelectedOption().getText();
    }

    @Override
    public void select(String optionText) {
        new Select(baseElement.locateElement()).selectByVisibleText(optionText);
    }

    @Override
    public List<String> getAvailableOptions() {
        return new Select(baseElement.locateElement()).getOptions()
                                                      .stream()
                                                      .map(option -> option.getText())
                                                      .collect(Collectors.toList());
    }
}

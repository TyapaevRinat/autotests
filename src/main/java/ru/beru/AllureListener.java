package ru.beru;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.StepResult;

@SuppressWarnings("JavadocType")
public class AllureListener extends Preset implements StepLifecycleListener {

    private void addAttachment(final StepResult result) {
        Attachment attachment = new Attachment();
        attachment.setType("image/png");
        attachment.setSource(SCREEN_PATH + "\\" + saveShot() + ".png");
        result.withAttachments(attachment);
    }

    @Override
    public void beforeStepStart(final StepResult result) {
        addAttachment(result);
    }

    @Override
    public void beforeStepStop(final StepResult result)    {
        addAttachment(result);
    }
}
package com.chutneytesting.idea.actions

import com.chutneytesting.idea.ChutneyUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.diagnostic.Logger


abstract class RemoteScenarioBaseAction : AnAction() {

    private val LOG = Logger.getInstance(RemoteScenarioBaseAction::class.java)

    override fun update(event: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = event.project
        val virtualFile = event.getData(DataKeys.VIRTUAL_FILE)
        val psiFile = event.getData(LangDataKeys.PSI_FILE)
        event.presentation.isEnabledAndVisible =
            project != null && psiFile != null && virtualFile != null && !virtualFile.isDirectory && ChutneyUtil.isRemoteChutneyJson(
                psiFile
            )
    }

}

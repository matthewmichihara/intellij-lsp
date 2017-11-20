package com.github.gtache.lsp.editor.listeners

import com.github.gtache.lsp.editor.EditorEventManager
import com.github.gtache.lsp.utils.Utils
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentSynchronizationVetoer

class LSPFileDocumentSynchronizationVetoer extends FileDocumentSynchronizationVetoer {
  override def maySaveDocument(document: Document, isSaveExplicit: Boolean): Boolean = {
    EditorEventManager.forUri(Utils.documentToUri(document)) match {
      case Some(m) => if (m.wrapper.isWillSaveWaitUntil && !m.needSave) false else super.maySaveDocument(document, isSaveExplicit)
      case None => super.maySaveDocument(document, isSaveExplicit)
    }
  }
}
interface KeyEventProcessor {
    void process(KeyEvent evt, ActionTextField context);
}

class KeyTypedProcessor implements KeyEventProcessor {
    public void process(KeyEvent evt, ActionTextField context) {
        char ch = evt.getKeyChar();
        if (!context.nonDigit && Character.isDigit(ch)) {
            context.superProcessKeyEvent(evt);
            context.repeat = true;
            context.repeatCount = Integer.parseInt(context.action.getText());
        } else {
            context.nonDigit = true;
            if (context.repeat) {
                context.passToView(evt);
            } else {
                context.superProcessKeyEvent(evt);
            }
        }
    }
}

class KeyPressedProcessor implements KeyEventProcessor {
    public void process(KeyEvent evt, ActionTextField context) {
        int keyCode = evt.getKeyCode();
        if (evt.isActionKey() || evt.isControlDown() || evt.isAltDown() || evt.isMetaDown()
            || keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE
            || keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_TAB
            || keyCode == KeyEvent.VK_ESCAPE) {
            context.nonDigit = true;
            if (context.repeat) {
                context.passToView(evt);
            } else if (keyCode == KeyEvent.VK_TAB) {
                context.complete(true);
                evt.consume();
            } else if (keyCode == KeyEvent.VK_ESCAPE) {
                context.handleEscapeKey(evt);
            } else if ((keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN)
                       && context.popup != null) {
                context.popup.list.processKeyEvent(evt);
            } else {
                context.superProcessKeyEvent(evt);
            }
        } else {
            context.superProcessKeyEvent(evt);
        }
    }
}

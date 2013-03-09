package damechinoises.SD;

import java.awt.Toolkit;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextLimiter extends PlainDocument {
	private static final long serialVersionUID = 6016644222758009922L;
 
	private int max;
 
	public TextLimiter(int max){
		super();
 
		this.max = max;
	}
 
	public void insertString(int offset, String str, javax.swing.text.AttributeSet attrs) throws BadLocationException {
		if (str != null && getLength() + str.length() > max) {
			Toolkit.getDefaultToolkit().beep();
		} else {
			super.insertString(offset, str, attrs);
		}
	}
 
	public void replace(int offset, int length, String str, javax.swing.text.AttributeSet attrs)
			throws BadLocationException {
		if (str != null && getLength() + str.length() - length > max) {
			Toolkit.getDefaultToolkit().beep();
		} else {
			super.replace(offset, length, str, attrs);
		}
	}
}
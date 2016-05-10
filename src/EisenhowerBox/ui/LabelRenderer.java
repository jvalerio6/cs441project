package EisenhowerBox.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import EisenhowerBox.Task;

class LabelRenderer extends JLabel implements ListCellRenderer {
    public LabelRenderer() {
      setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {

      if (value != null) {
    	  if (value instanceof Task) {
    		  Task t = (Task)value;
    	      String name = t.getTskName();
    	      setText(name);
    	  }

    	  else if (value instanceof String) {
    		  setText(value.toString());
    	  }

      }

      setBackground(isSelected ? Color.getHSBColor(0.15f, .12f, 0.80f) : Color.WHITE);
      setForeground(isSelected ? Color.WHITE : Color.DARK_GRAY);

      return this;
    }
}
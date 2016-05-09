package EisenhowerBox.ui;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import EisenhowerBox.Task;

class PartsListModel extends DefaultListModel implements ComboBoxModel {

    Object currentValue;

    public PartsListModel(List<Task> selectedTasks) {

    	for (int i = 0, n = selectedTasks.size(); i < n; i++) {
    		addElement(selectedTasks.get(i));
    	}
    }

    // ComboBoxModel methods
    public Object getSelectedItem() {
      return currentValue;
    }

    public void setSelectedItem(Object anObject) {
      currentValue = anObject;
      fireContentsChanged(this, -1, -1);
    }
}
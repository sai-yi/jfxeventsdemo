package zero.saiyi.jfxeventsdemo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerDemo {
	@FXML
	private TextField nameField;
	@FXML
	private Button helloBtn;
	@FXML
	private Button byeBtn;
	@FXML
	private CheckBox clearTextCheckBox;
	@FXML
	private Label labelResult;
	
	@FXML
	public void initialize() {
				
		helloBtn.setDisable(true);
		byeBtn.setDisable(true);
	}
	
	
	@FXML
	public void onClickedEvent(ActionEvent e) {
//		System.out.println("Hello , "+nameField.getText());
//		System.out.println(e.getSource().toString());
		if(e.getSource().equals(helloBtn)) {
			System.out.println("Hello , "+nameField.getText());
		}else if(e.getSource().equals(byeBtn)) {
			System.out.println("Bye , "+nameField.getText());
		}
//		System.out.println(clearTextCheckBox.isSelected()?"Checked":"not checked");
//		try {
//			Thread.sleep(5000);
//			System.out.println("Wait 5 sec..");
//		}catch(InterruptedException ie) {
//			System.out.println(ie.getMessage());
//		}
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				String note=Platform.isFxApplicationThread()?"UI Thread": "Background Thread";
				System.out.println("Now I'm going to sleep on the "+note);
				try {
				Thread.sleep(10000);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						labelResult.setText("background update is completed!!");
						String note=Platform.isFxApplicationThread()?"UI Thread": "Background Thread";
						System.out.println(nameField.getText());
						System.out.println("Now I'm going to update to the  "+note);
					}
				});
			}catch(InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
				
			}
		};
		//runnable.run();
		new Thread(runnable).start();
		
		
		if(clearTextCheckBox.isSelected()) {
			nameField.clear();
			helloBtn.setDisable(true);
			byeBtn.setDisable(true);
		}
		
	}
	@FXML
	public void textFieldReleaseEvents() {
		String name=nameField.getText();
		// if name is empty enableBtn  = true btns will be disable
		// otherwise  enableBtn = false btns will be enable
		boolean enableBtn = name.isEmpty() || name.trim().isEmpty();
		helloBtn.setDisable(enableBtn);
		byeBtn.setDisable(enableBtn);
	}

	public void checkBoxEvent() {
		System.out.println("clear text checkbox is "+(clearTextCheckBox.isSelected()?"checked":"not checked"));
	}
	
}

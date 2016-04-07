/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockhw;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventType;
import javafx.scene.layout.GridPane;
import javafx.scene.control.SplitPane;
import javafx.util.Duration;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eric
 */
public class ClockHW extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ClockPane clock1=new ClockPane();
        ClockPane clock2=new ClockPane();
        ClockPane clock3=new ClockPane();
        ClockPane clock4=new ClockPane();
        Label lab1=new Label();
        Label lab2=new Label();
        Label lab3=new Label();
        Label lab4=new Label();
        lab1.setText("        Taipei");
        lab1.setMinSize(300, 50);
        lab2.setText("        Tokyo");
        lab2.setMinSize(300, 50);
        lab3.setText("        NewYork");
        lab3.setMinSize(300, 50);
        lab4.setText("        London");
        lab4.setMinSize(300, 50);
        
       
        EventHandler<ActionEvent> eventHandler=e -> {
            clock1.setCurrentTime(0);
            
            
            clock2.setCurrentTime(1);
            
            
            clock3.setCurrentTime(12);
            clock4.setCurrentTime(8);
            
            lab1.setText("           Taipei         " + clock1.displaytime());
            lab2.setText("           Tokyo         " + clock2.displaytime());
            lab3.setText("           NewYork         " + clock3.displaytime());
            lab4.setText("           London         " + clock4.displaytime());
        };
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        */
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        
        GridPane parentgrid=new GridPane();
        GridPane gd1=new GridPane();
        GridPane gd2=new GridPane();
        GridPane gd3=new GridPane();
        GridPane gd4=new GridPane();
        
        parentgrid.add(gd1, 0, 0);
        parentgrid.add(gd2, 0, 1);
        parentgrid.add(gd3, 1, 0);
        parentgrid.add(gd4, 1, 1);
        
        /*
        gd1.maxHeight(400);
        gd1.maxWidth(400);
        gd2.maxHeight(400);
        gd2.maxWidth(400);
        gd3.maxHeight(400);
        gd3.maxWidth(400);
        gd4.maxHeight(400);
        gd4.maxWidth(400);
        */
        gd1.add(lab1, 0, 0);
        gd1.add(clock1, 0, 1);        
        
        gd2.add(lab2, 0, 0);
        gd2.add(clock2, 0, 1);
        
        gd3.add(lab3, 0, 0);
        gd3.add(clock3, 0, 1);
        
        gd4.add(lab4, 0, 0);
        gd4.add(clock4, 0, 1);
        
        
        
        //Scene scene = new Scene(clock, 300, 250);
        Scene scene = new Scene(parentgrid, 600, 800);
        
        primaryStage.setTitle("ClockAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


class ClockPane extends Pane {
  private int hour;
  private int minute;
  private int second;
  private int ampm;

  // Clock pane's width and height
  private double w = 300, h = 300;
  
  /** Construct a default clock with the current time*/
  public ClockPane() {
    setCurrentTime(0);
  }

  /** Construct a clock with specified hour, minute, and second */
  public ClockPane(int hour, int minute, int second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    paintClock();
  }

  public String displaytime(){
      Date date = new Date();
      String tmpstr=new String();
      String apmstr=new String();
      
      //String strDateFormat = "HH:mm:ss a";
      //SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
      //tmpstr=sdf.format(date);
      //System.out.println(tmpstr);
      if(ampm==Calendar.AM){
          apmstr="AM";
      }
      else{
          apmstr="PM";
      }
      tmpstr=String.format("%02d:%02d:%02d  %s", hour, minute, second, apmstr);
      
      
      return tmpstr;
  }      
  
  
  
  /** Return hour */
  public int getHour() {
    return hour;
  }

  /** Set a new hour */
  public void setHour(int hour) {
    this.hour = hour;
    paintClock();
  }

  /** Return minute */
  public int getMinute() {
    return minute;
  }

  /** Set a new minute */
  public void setMinute(int minute) {
    this.minute = minute;
    paintClock();
  }

  /** Return second */
  public int getSecond() {
    return second;
  }

  /** Set a new second */
  public void setSecond(int second) {
    this.second = second;
    paintClock();
  }

  /** Return clock pane's width */ 
  public double getW() {
    return w;
  }
  
  /** Set clock pane's width */ 
  public void setW(double w) {
    this.w = w;
    paintClock();
  }
  
  /** Return clock pane's height */ 
  public double getH() {
    return h;
  }
  
  /** Set clock pane's height */ 
  public void setH(double h) {
    this.h = h;
    paintClock();
  }
  
  /* Set the current time for the clock */
  public void setCurrentTime(int offsethour) {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();

    // Set current hour, minute and second
    this.hour = calendar.get(Calendar.HOUR_OF_DAY)+offsethour;
    if(this.hour>24){
        this.hour=this.hour-24;
    }
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);
    //this.ampm=calendar.get(Calendar.AM_PM);
    if(this.hour<12 && this.hour>=0){
        this.ampm=Calendar.AM;                
    }
    else if(this.hour<24 && this.hour>=12){
        this.ampm=Calendar.PM;                
    }    
    
    if(this.hour>12){
        this.hour=this.hour-12;
    }
    paintClock(); // Repaint the clock
  }
  
  /** Paint the clock */
  private void paintClock() {
    // Initialize clock parameters
    double clockRadius = Math.min(w, h) * 0.8 * 0.5;
    double centerX = w / 2;
    double centerY = h / 2;

    // Draw circle
    Circle circle = new Circle(centerX, centerY, clockRadius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
    Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
    Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
    Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
    
    // Draw second hand
    double sLength = clockRadius * 0.8;
    double secondX = centerX + sLength * 
      Math.sin(second * (2 * Math.PI / 60));
    double secondY = centerY - sLength * 
      Math.cos(second * (2 * Math.PI / 60));
    Line sLine = new Line(centerX, centerY, secondX, secondY);
    sLine.setStroke(Color.RED);

    // Draw minute hand
    double mLength = clockRadius * 0.65;
    double xMinute = centerX + mLength * 
      Math.sin(minute * (2 * Math.PI / 60));
    double minuteY = centerY - mLength * 
      Math.cos(minute * (2 * Math.PI / 60));
    Line mLine = new Line(centerX, centerY, xMinute, minuteY);
    mLine.setStroke(Color.BLUE);
    
    // Draw hour hand
    double hLength = clockRadius * 0.5;
    double hourX = centerX + hLength * 
      Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    double hourY = centerY - hLength *
      Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    Line hLine = new Line(centerX, centerY, hourX, hourY);
    hLine.setStroke(Color.GREEN);
    
    getChildren().clear();  
    getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
  }
}
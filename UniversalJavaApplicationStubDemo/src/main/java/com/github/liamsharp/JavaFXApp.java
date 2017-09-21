package com.github.liamsharp;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

import com.sun.glass.ui.Application.EventHandler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class JavaFXApp extends Application
{
    private static final int MB = 1024 * 1024;

    
    private static void showAlert(
        final File file)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!" + file);
        
        alert.showAndWait();
    }

    
    @Override
    public void start(
        final Stage primaryStage) 
    {
        setOpenFileRequestHander(".txt", (file) -> showAlert(file));
        final long maxMemory = Runtime.getRuntime().maxMemory() / MB;
        
        final Label label = new Label(
                "Hello!\n" + 
                "params: " + getParameters().getRaw() + "\n" +
                "maxMemory: " + maxMemory + "M");

        primaryStage.setTitle(System.getProperty("java.version") + " " + System.getProperty("java.vendor") + " " + System.getProperty("java.vendor.url"));
        StackPane root = new StackPane();
        root.getChildren().add(label);
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public static void setOpenFileRequestHander(
        final String fileSuffix,
        final Consumer<File> openFileHandler)
    {
        final com.sun.glass.ui.Application application = com.sun.glass.ui.Application.GetApplication();
        application.setEventHandler(
                new JavaFXApp.ApplicationEventHandler(
                        application.getEventHandler(),
                        fileSuffix,
                        openFileHandler));
    }

    /*
     * Proxies all events to the original event handler, except the open files event.
     */
    static class ApplicationEventHandler extends EventHandler
    {
        private final EventHandler mOriginalEventHandler;
        private final String mFileSuffix;
        private final Consumer<File> mOpenFileHandler;
        
        public ApplicationEventHandler(
            final EventHandler originalEventHandler,
            final String fileSuffix,
            final Consumer<File> openFileHandler)
        {
            mOriginalEventHandler = originalEventHandler;
            mFileSuffix = fileSuffix;
            mOpenFileHandler = openFileHandler;
        }
        
        @Override
        public void handleOpenFilesAction(
            final com.sun.glass.ui.Application app,
            final long time,
            final String[] files)
        {
            if (files.length > 0)
            {
                final String filePath = files[0];
                mOpenFileHandler.accept(new File(filePath));
            }
        }

        @Override
        public void handleWillFinishLaunchingAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleWillFinishLaunchingAction(app, time);
        }

        @Override
        public void handleDidFinishLaunchingAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleDidFinishLaunchingAction(app, time);
        }

        @Override
        public void handleWillBecomeActiveAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleWillBecomeActiveAction(app, time);
        }

        @Override
        public void handleDidBecomeActiveAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleDidBecomeActiveAction(app, time);
        }

        @Override
        public void handleWillResignActiveAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleWillResignActiveAction(app, time);
        }

        @Override
        public void handleDidResignActiveAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleDidResignActiveAction(app, time);
        }

        @Override
        public void handleDidReceiveMemoryWarning(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleDidReceiveMemoryWarning(app, time);
        }

        @Override
        public void handleWillHideAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleWillHideAction(app, time);
        }

        @Override
        public void handleDidHideAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleDidHideAction(app, time);
        }

        @Override
        public void handleWillUnhideAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleWillUnhideAction(app, time);
        }

        @Override
        public void handleDidUnhideAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleDidUnhideAction(app, time);
        }

        @Override
        public void handleQuitAction(
            final com.sun.glass.ui.Application app,
            final long time)
        {
            mOriginalEventHandler.handleQuitAction(app, time);
        }

        @Override
        public boolean handleThemeChanged(
            final String themeName)
        {
            return mOriginalEventHandler.handleThemeChanged(themeName);
        }
    }

}

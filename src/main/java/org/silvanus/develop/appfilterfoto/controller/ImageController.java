package org.silvanus.develop.appfilterfoto.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.silvanus.develop.appfilterfoto.model.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ImageController implements Initializable {

    @FXML private ComboBox<String> filterComboBox;
    @FXML private Button uploadButton, applyButton, resetButton, downloadButton;
    @FXML private ImageView originalImageView, filteredImageView;
    @FXML private CheckBox previewCheckBox;

    private BufferedImage originalImage;
    private BufferedImage filteredImage;
    private final Map<String, Filter> filters = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        filters.put("Grayscale", new GrayscaleFilter());
        filters.put("Sepia", new SepiaFilter());
        filters.put("Blur", new BlurFilter());
        filters.put("Vignette", new VignetteFilter());
        filters.put("Brightness", new BrightnessFilter(30));

        filterComboBox.getItems().addAll(filters.keySet());

        previewCheckBox.setSelected(false);
        previewCheckBox.setOnAction(e -> togglePreview(previewCheckBox.isSelected()));

        showSingleImage();
    }

    private void togglePreview(boolean enabled) {
        if (enabled) {
            showBothImages();
        } else {
            showSingleImage();
        }
    }

    //ShowSingleImage Lama
//    private void showSingleImage() {
//        if (originalImage != null) {
//            filteredImageView.setVisible(false);
//            originalImageView.setImage(ImageUtils.toFXImage(filteredImage));
//            originalImageView.setVisible(true);
//        }
//    }

    //ShowSingleImage Baru
    private void showSingleImage() {
        if (originalImage != null) {
            originalImageView.setVisible(true);
            filteredImageView.setVisible(false);

            // Gunakan originalImage jika filteredImage belum ada
            if (filteredImage != null) {
                originalImageView.setImage(ImageUtils.toFXImage(filteredImage));
            } else {
                originalImageView.setImage(ImageUtils.toFXImage(originalImage));
            }
        }
    }

    private void showBothImages() {
        if (originalImage != null && filteredImage != null) {
            originalImageView.setImage(ImageUtils.toFXImage(originalImage));
            filteredImageView.setImage(ImageUtils.toFXImage(filteredImage));
            originalImageView.setVisible(true);
            filteredImageView.setVisible(true);
        }
    }

    @FXML
    private void handleUpload(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar");
        fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            try {
                originalImage = ImageUtils.loadImage(file);
                filteredImage = originalImage;
                originalImageView.setImage(ImageUtils.toFXImage(originalImage));
                filteredImageView.setImage(null);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    //handleApply Lama
//    @FXML
//    private void handleApply() {
//        String selected = filterComboBox.getValue();
//        if (selected != null && originalImage != null) {
//            Filter filter = filters.get(selected);
//            filteredImage = filter.apply(originalImage);
//            filteredImageView.setImage(ImageUtils.toFXImage(filteredImage));
//        }
//    }

    //handleApply baru
    @FXML
    private void handleApply() {
        String selected = filterComboBox.getValue();
        if (selected != null && originalImage != null) {
            Filter filter = filters.get(selected);
            filteredImage = filter.apply(originalImage);

            if (previewCheckBox.isSelected()) {
                // Preview aktif: tampilkan dua gambar
                filteredImageView.setImage(ImageUtils.toFXImage(filteredImage));
                originalImageView.setImage(ImageUtils.toFXImage(originalImage));
            } else {
                // Preview mati: tampilkan hasil filter menggantikan gambar utama
                originalImageView.setImage(ImageUtils.toFXImage(filteredImage));
                filteredImageView.setImage(null); // kosongkan ImageView kedua
            }
        }
    }


    //Reset Image lama
//    @FXML
//    private void resetImage() {
////        if (originalImage != null) {
////            filteredImageView.setImage(null);
////        }
//        if (originalImage != null) {
//            filteredImage = originalImage;
//            filteredImageView.setImage(ImageUtils.toFXImage(originalImage));
//        }
//    }

    //Reset Image Baru
    @FXML
    private void resetImage() {
        if (originalImage != null) {
            filteredImage = null;

            if (previewCheckBox.isSelected()) {
                // Mode dua gambar: tampilkan gambar original saja, kosongkan filtered
                originalImageView.setImage(ImageUtils.toFXImage(originalImage));
                filteredImageView.setImage(null);
            } else {
                // Mode satu gambar: tampilkan kembali gambar original di imageView utama
                originalImageView.setImage(ImageUtils.toFXImage(originalImage));
                filteredImageView.setImage(null);
            }

            filterComboBox.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void handleSave() {
        if (filteredImage != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Simpan Gambar");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {
                    ImageUtils.saveImage(filteredImage, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
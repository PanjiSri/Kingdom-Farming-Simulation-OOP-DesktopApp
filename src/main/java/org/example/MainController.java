package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Hewan.Hewan;
import org.example.card.Item.Item;
import org.example.card.Item.Protect;
import org.example.card.Produk.*;
import org.example.card.Tumbuhan.Tumbuhan;
import plugin.PluginLoader;
import plugin.TXTLoader;
import plugin.TXTSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {

    private Stage primary;
    private File nama_file;
    private boolean isXMLloaded = false;

    @FXML
    private GridPane ladang, shuffle_panel, deck_aktif;

    @FXML
    private AnchorPane info_pane;

    @FXML
    private Pane plugin_pane, pane_ladang, ambil_kartu, jumlah_turn, player_saat_ini, save_load;

    @FXML
    private Button upload, save11, next_turn, shuffle_card, close_button, ladang_lawan, ladang_sendiri, panen, tutup_info, save, load_progress, save_to_main, save_progress, plugin_to_main;

    @FXML
    private StackPane board;

    @FXML
    private ComboBox<String> format_save, format_load;

    @FXML
    private VBox info_hewan;

    @FXML
    private TextField folder_load, folder_save;

    @FXML
    private Button upload_file, submit_file; 

    // Toko
    @FXML
    private Button toko_kembali, toko_buka;
    @FXML
    private Pane toko;
    @FXML
    private AnchorPane sirip_hiu, susu, daging_domba, daging_kuda, telur, daging_beruang, jagung, labu, stroberi;
    @FXML
    private Label jumlah_sirip_hiu, jumlah_susu, jumlah_daging_domba, jumlah_daging_kuda, jumlah_telur, jumlah_daging_beruang, jumlah_jagung, jumlah_labu, jumlah_stroberi;

    @FXML
    private Label uang_player1, uang_player2;

    private Board main;
    List<String> gameState;
    List<String> player1;
    List<String> player2;

    ArrayList<String> gameStatesave;
    ArrayList<String> player1save;
    ArrayList<String> player2save;

    private String style = ("-fx-pref-width: 70.0;" +
                            "-fx-pref-height: 90.0;" +
                            "-fx-border-color: black;" +
                            "-fx-border-width: 1;" +
                            "-fx-background-color: beige;" +
                            "-fx-padding: 5;" +
                            "-fx-spacing: 5;" +
                            "-fx-alignment: center;" +
                            "-fx-border-radius: 10;" +
                            "-fx-background-radius: 10;");

    private String font = ("-fx-font-size: 14;" +
                           "-fx-font-weight: bold;" + 
                           "-fx-font-family: 'Comic Sans MS';");

    private int width = 90, height = 90;

    // Menyediakan ladang kosong
    public void init() {
        // Initialize the grid cells
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Pane pane = new Pane();
                pane.setStyle(style);
                ladang.add(pane, i, j);
            }
        }

        // Set drag over for the GridPane
        ladang.setOnDragOver(event -> {
            if (event.getGestureSource() != ladang && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Set drop for the GridPane
        ladang.setOnDragDropped(event -> {
            Player a = main.getPlayernow();
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && db.getString().equals("pane")) {
                Pane draggedPane = (Pane) event.getGestureSource();
                draggedPane.setStyle(style);

                int col = (int) (event.getX() / (ladang.getWidth() / ladang.getColumnCount()));
                int row = (int) (event.getY() / (ladang.getHeight() / ladang.getRowCount()));
                // Hapus dari deck_aktif hanya jika Pane berasal dari deck_aktif
                if (deck_aktif.getChildren().contains(draggedPane)) {
                    deck_aktif.getChildren().remove(draggedPane);
                    String id = draggedPane.getId();
                    int idx_card_deck_aktif = a.get_card_aktif_idx(id);
                    a.add_in_lahan(row, col, a.get_card_aktif(idx_card_deck_aktif));
                    a.drop_deck_aktif(a.get_card_aktif(idx_card_deck_aktif));

                    // Tambahkan ke ladang
                    ladang.add(draggedPane, col, row);
                    a.print_lahan();
                    success = true;
                }

                else if (ladang.getChildren().contains(draggedPane)) {
                    ladang.getChildren().remove(draggedPane);
                    System.out.println(draggedPane.getId());
                    String id = draggedPane.getId();
                    ArrayList<Integer>  idx_lahan = a.get_idx_lahan(id);
                    a.add_in_lahan(row, col, a.get_card_ladang(idx_lahan.get(0), idx_lahan.get(1)));
                    a.drop_ladang(idx_lahan.get(0), idx_lahan.get(1));
                    // Tambahkan ke ladang
                    a.print_lahan();
                    success = true;
                    ladang.getChildren().remove(draggedPane);
                    ladang.add(draggedPane, col, row);
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // toko
        board.getChildren().remove(toko);

        // Initialize the action buttons
        initialize_click();
    }

    // Cek ladang lawan
    public void ladang_lawan() {
        ladang.getChildren().clear();
        init();
        Player a = main.getEnemyNow();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (a.get_card_ladang(i, j) != null) {
                    Card card = a.get_card_ladang(i, j);

                    VBox pane = new VBox();
                    pane.setStyle(style);

                    Image image = new Image(this.getClass().getResource(card.getImgPath()).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(width);
                    imageView.setFitHeight(height);
                    pane.setId(a.get_card_ladang(i, j).getName()); // Menggunakan ID unik berdasarkan posisi

                    Label label = new Label(card.getName());
                    label.setStyle(font);
                    pane.getChildren().add(label);
                    pane.getChildren().add(imageView);

                    int id = a.get_card_ladang(i, j).getId();

                    pane.setId(Integer.toString(id));pane.setOnDragOver(event -> {
                        if (event.getGestureSource() != ladang && event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                    });
                    pane.setOnDragDropped(event -> {
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (db.hasString() && db.getString().equals("pane")) {
                            Pane draggedPane = (Pane) event.getGestureSource();
                            draggedPane.setStyle(style);

                            // Hapus dari deck_aktif hanya jika Pane berasal dari deck_aktif
                            if (deck_aktif.getChildren().contains(draggedPane)) {
                                deck_aktif.getChildren().remove(draggedPane);

                                // Pastikan tidak ada elemen pada posisi tersebut
                                System.out.println(draggedPane.getId());
                                String id_dragged = draggedPane.getId();
                                int idx_card_deck_aktif = a.get_card_aktif_idx(id_dragged);
                                Card card_dragged = a.get_card_aktif(idx_card_deck_aktif);
                                if (card_dragged instanceof Item) {
                                    ((Item) card_dragged).aksi((BisaPanen) card);
                                }
                                if (card_dragged instanceof Produk) {
                                    ((Produk) card_dragged).aksi((BisaPanen) card);
                                }
                                a.drop_deck_aktif(a.get_card_aktif(idx_card_deck_aktif));
                            }
                            success = true;
                        }
                        event.setDropCompleted(success);
                        event.consume();
                    });
                    ladang.add(pane, j, i);
                }
            }
        }
    }

    public void show_save() {
        board.getChildren().clear();
        board.getChildren().add(save_load);
    }

    // Set fungsi pda button
    public void initialize_click() {
        next_turn.setOnAction(e -> {
            if (main.getTotalturn() > 20) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                Player player_1 = main.getP1();
                Player player_2 = main.getP2();

                if (player_1.getCoin() > player_2.getCoin()) {
                    int koin = player_1.getCoin();
                    alert.setTitle("Player 1 Win");
                    alert.setHeaderText("Player 1 Win");
                    alert.setContentText("Player 1 Menang dengan " + koin + " koin");
                    alert.showAndWait();
                } else if (player_1.getCoin() < player_2.getCoin()) {
                    int koin = player_2.getCoin();
                    alert.setTitle("Player 2 Win");
                    alert.setHeaderText("Player 2 Win");
                    alert.setContentText("Player 2 Menang dengan " + koin + " koin");
                    alert.showAndWait();
                } else {
                    int koin = player_1.getCoin();
                    alert.setTitle("Draw");
                    alert.setHeaderText("Draw");
                    alert.setContentText("Draw dengan kedua player memiliki " + koin + " koin");
                    alert.showAndWait();
                }
                System.exit(0);
            }
            Player a = main.getPlayernow();
            a.add_umur_tumbuhan();
            a.delete_item_aktiv();
            change_to_shuffle();
        });
        shuffle_card.setOnAction(e -> shuffle_kartu());
        close_button.setOnAction(e -> change_to_main());
        ladang_lawan.setOnAction(e -> ladang_lawan());
        ladang_sendiri.setOnAction(e -> change_to_main());
        // panen.setOnAction(e -> panen());
        tutup_info.setOnAction(e -> {
            info_hewan.getChildren().clear();
            board.getChildren().addAll(ambil_kartu, pane_ladang);
        });
        toko_buka.setOnAction(e -> main_to_toko());
        toko_kembali.setOnAction(e -> toko_to_main());
        save.setOnAction(e -> show_save());
        load_progress.setOnAction(e -> {
            String path = folder_load.getText();
            loadTXT(path);
            if (format_load.getValue() == "XAML") {
                System.out.println("XAML");
            }
            if (format_load.getValue() == "JSON") {
                System.out.println("JSON");
            }
            else {
                System.out.println("TXT");
            }
        });
        save_to_main.setOnAction(e -> change_to_main());
        save_progress.setOnAction(e -> {
            Player p1 = main.getP1();
            Player p2 = main.getP2();
            player1save = p1.get_save();
            player2save = p2.get_save();
            gameStatesave = getstatesave();
            saveTXT(gameStatesave, player1save, player2save, folder_save.getText());
            if (format_save.getValue() == "XAML") {
                System.out.println("XAML");
            }
            if (format_save.getValue() == "JSON") {
                System.out.println("JSON");
            }
            else {
                System.out.println("TXT");
            }
        });
        save11.setOnAction(e -> show_plugin());
        upload_file.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            nama_file = fileChooser.showOpenDialog(primary);
            if (nama_file != null) {
                System.out.println("Selected file: " + nama_file.getAbsolutePath());
            }
        });
        submit_file.setOnAction(e -> {
            PluginLoader pluginLoader = new PluginLoader();
            try {
                pluginLoader.loadPlugin("plugin_jar/XMLPlugin.jar", "plugin.XMLPlugin");
                isXMLloaded = true;
                System.out.println("Berhasil load plugin");
                if (isXMLloaded == true) {
                    change_format();
                }
            }
            catch (Exception ex) {
                System.out.println("Failed load plugin");
            }
        });
        plugin_to_main.setOnAction(e -> change_to_main());
    }

    public ArrayList<String> getstatesave() {
        int get = 0;
        ArrayList<String> list = new ArrayList<>();
        Toko toko = main.getToko();
        list.add(Integer.toString(main.getTotalturn()));
        if (toko.ambilStokProduk("DAGING_DOMBA") > 0) {
            list.add("DAGING_DOMBA " + Integer.toString(toko.ambilStokProduk("DAGING_DOMBA")));
            get++;
        }
        if (toko.ambilStokProduk("DAGING_KUDA") > 0) {
            list.add("DAGING_KUDA " + Integer.toString(toko.ambilStokProduk("DAGING_KUDA")));
            get++;
        }
        if (toko.ambilStokProduk("DAGING_BERUANG") > 0) {
            list.add("DAGING_BERUANG " + Integer.toString(toko.ambilStokProduk("DAGING_BERUANG")));
            get++;
        }
        if (toko.ambilStokProduk("JAGUNG") > 0) {
            list.add("JAGUNG " + Integer.toString(toko.ambilStokProduk("JAGUNG")));
            get++;
        }
        if (toko.ambilStokProduk("LABU") > 0) {
            list.add("LABU " + Integer.toString(toko.ambilStokProduk("LABU")));
            get++;
        }
        if (toko.ambilStokProduk("SIRIP_HIU") > 0) {
            list.add("SIRIP_HIU " + Integer.toString(toko.ambilStokProduk("SIRIP_HIU")));
            get++;
        }
        if (toko.ambilStokProduk("STROBERI") > 0) {
            list.add("STROBERI " + Integer.toString(toko.ambilStokProduk("STROBERI")));
            get++;
        }
        if (toko.ambilStokProduk("SUSU") > 0) {
            list.add("SUSU " + Integer.toString(toko.ambilStokProduk("SUSU")));
            get++;
        }
        if (toko.ambilStokProduk("TELUR") > 0) {
            list.add("TELUR " + Integer.toString(toko.ambilStokProduk("TELUR")));
            get++;
        }
        if(get > 0) {
            list.add(1, Integer.toString(get));
        }
        return list;
    }

    public void show_plugin() {
        board.getChildren().clear();
        board.getChildren().add(plugin_pane);
    }
    // Shuffle kartu
    public void shuffle_kartu() {
        Player a = main.getPlayernow();
        a.shuffle();
        add_kartu_ke_shuffle_field();
    }

    // Tambahkan kartu ke deck aktif
    public void add_to_deck_aktif() {
        deck_aktif.getChildren().clear();
        Player a = main.getPlayernow();
        for (int i = 0; i < 6; i++) {
            if (a.get_card_aktif(i) != null) {
                Image image = new Image(this.getClass().getResource(a.get_card_aktif(i).getImgPath()).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(width);
                imageView.setFitHeight(height);
                
                VBox card = new VBox();
                Label label = new Label(a.get_card_aktif(i).getName());
                String id = Integer.toString(a.get_card_aktif(i).getId());

                card.setId(id);
                label.setStyle(font);
                card.getChildren().add(label);
                card.getChildren().add(imageView);
                card.setStyle(style);
                card.setOnDragDetected(event -> {
                    Dragboard db = card.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString("pane");
                    db.setContent(content);
                    event.consume();
                });
                deck_aktif.add(card, i, 0);
            }
        }
    }

    // Tambahkan kartu ke ladang
    public void add_to_ladang() {
        Player a = main.getPlayernow();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Card card = a.get_card_ladang(i, j);
                // Pastikan kartu tidak null atau tidak valid
                if (card != null) {
                    VBox pane = new VBox();
                    pane.setStyle(style);

                    Image image = new Image(this.getClass().getResource(card.getImgPath()).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(width);
                    imageView.setFitHeight(height);
                    pane.setId(a.get_card_ladang(i, j).getName()); // Menggunakan ID unik berdasarkan posisi

                    Label label = new Label(card.getName());
                    label.setStyle(font);

                    String id = Integer.toString(card.getId());
                    pane.setId(id);
                    pane.getChildren().add(label);
                    pane.getChildren().add(imageView);
                    pane.setOnDragDetected(event -> {
                        Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString("pane");
                        db.setContent(content);
                        event.consume();
                    });
                    pane.setOnDragOver(event -> {
                        if (event.getGestureSource() != ladang && event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                    });
                    pane.setOnDragDropped(event -> {
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (db.hasString() && db.getString().equals("pane")) {
                            Pane draggedPane = (Pane) event.getGestureSource();
                            draggedPane.setStyle(style);

                            // Hapus dari deck_aktif hanya jika Pane berasal dari deck_aktif
                            if (deck_aktif.getChildren().contains(draggedPane)) {
                                deck_aktif.getChildren().remove(draggedPane);

                                // Pastikan tidak ada elemen pada posisi tersebut
                                System.out.println(draggedPane.getId());
                                String id_dragged = draggedPane.getId();
                                int idx_card_deck_aktif = a.get_card_aktif_idx(id_dragged);
                                Card card_dragged = a.get_card_aktif(idx_card_deck_aktif);
                                if (card_dragged instanceof Item) {
                                    ((Item) card_dragged).aksi((BisaPanen) card);
                                }
                                if (card_dragged instanceof Produk) {
                                    ((Produk) card_dragged).aksi((BisaPanen) card);
                                }
                                a.drop_deck_aktif(a.get_card_aktif(idx_card_deck_aktif));
                            }
                            success = true;
                        }
                        event.setDropCompleted(success);
                        event.consume();
                    });
                    final int x = i;
                    final int y = j;
                    pane.setOnMouseClicked(event -> {
                        show_info(card, x, y);
                    });
                        ladang.add(pane, j, i);
                }
            }
        }
    }

    public void show_info(Card card, int x, int y) {
        board.getChildren().clear();
        Player player = main.getPlayernow();
        if (card instanceof Hewan) {
            Hewan hewan = (Hewan) card;

            VBox nama_hewan = new VBox();

            Image image = new Image(this.getClass().getResource(hewan.getImgPath()).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            Label label = new Label(hewan.getName());
            label.setStyle(font);
            nama_hewan.getChildren().addAll(label, imageView);
            nama_hewan.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-spacing: 10; -fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");
            info_hewan.getChildren().addAll(nama_hewan);

            label = new Label("Berat : " + hewan.getBerat());
            info_hewan.getChildren().addAll(label);

            label = new Label("Standar Berat Panen : " + hewan.getStandarBeratPanen());
            info_hewan.getChildren().addAll(label);

            StringBuilder item = new StringBuilder("Item Aktif : ");
            for (String key : hewan.getItem().keySet()) {
                if (hewan.getItem().get(key) > 0) {
                    item.append(key).append(" (").append(hewan.getItem().get(key)).append("), ");
                }
            }
            label = new Label(item.toString());
            info_hewan.getChildren().addAll(label);

            if (hewan.isSiapPanen()) {
                if (player.get_deck_aktif_size() < 6) {
                    panen.setDisable(false);
                    panen.setOnMouseClicked(e -> {
                        Produk produk = hewan.panen();
                        player.add_into_deck_aktiv(produk);
                        player.delete_from_ladang(x, y);
                        info_hewan.getChildren().clear();
                        board.getChildren().addAll(ambil_kartu, pane_ladang);
                        change_to_main();
                    });
                } else {
                    panen.setDisable(true);
                }
            } else {
                panen.setDisable(true);
            }
        }
        else if (card instanceof Tumbuhan) {
            Tumbuhan tumbuhan = (Tumbuhan) card;

            VBox nama_tumbuhan = new VBox();

            Image image = new Image(this.getClass().getResource(tumbuhan.getImgPath()).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            Label label = new Label(tumbuhan.getName());
            label.setStyle(font);
            nama_tumbuhan.getChildren().addAll(label, imageView);
            nama_tumbuhan.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-spacing: 10; -fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");
            info_hewan.getChildren().addAll(nama_tumbuhan);

            label = new Label("Umur : " + tumbuhan.getUmur());
            info_hewan.getChildren().addAll(label);

            label = new Label("Standar Umur Panen : " + tumbuhan.getStandarUmurPanen());
            info_hewan.getChildren().addAll(label);

            StringBuilder item = new StringBuilder("Item Aktif : ");
            for (String key : tumbuhan.getItem().keySet()) {
                if (tumbuhan.getItem().get(key) > 0) {
                    item.append(key).append(" (").append(tumbuhan.getItem().get(key)).append("), ");
                }
            }
            label = new Label(item.toString());
            info_hewan.getChildren().addAll(label);

            if (tumbuhan.isSiapPanen()) {
                if (player.get_deck_aktif_size() < 6) {
                    panen.setDisable(false);
                    panen.setOnMouseClicked(e -> {
                        Produk produk = tumbuhan.panen();
                        player.add_into_deck_aktiv(produk);
                        player.delete_from_ladang(x, y);
                        info_hewan.getChildren().clear();
                        board.getChildren().addAll(ambil_kartu, pane_ladang);
                        change_to_main();
                    });
                } else {
                    panen.setDisable(true);
                }
            } else {
                panen.setDisable(true);
            }
        }
        board.getChildren().addAll(info_pane);
    }

    // Ubah ke pane shuffle
    public void change_to_shuffle() {
        main.add_turn();
        Player a = main.getPlayernow();
        a.reset_ciot();
        board.getChildren().clear();
        add_kartu_ke_shuffle_field();
        board.getChildren().setAll(pane_ladang, ambil_kartu);
    }

    // Ubah ke pane main
    public void change_to_main() {
        Player a = main.getPlayernow();
        jumlah_turn.getChildren().clear();
        player_saat_ini.getChildren().clear();
        a.print_deck_aktif();
        set_turn();
        set_player();
        init();
        add_to_deck_aktif();
        add_to_ladang();
        a.print_lahan();
        board.getChildren().clear();
        board.getChildren().setAll(ambil_kartu, pane_ladang);
        Player p1 = main.getP1();
        Player p2 = main.getP2();
        uang_player1.setText(Integer.toString(p1.getCoin()));
        uang_player2.setText(Integer.toString(p2.getCoin()));
    }

    // Membuka pane toko
    public void main_to_toko() {
        Toko toko_board = main.getToko();
        board.getChildren().add(toko);
        jumlah_daging_beruang.setText(Integer.toString(toko_board.ambilStokProduk("DAGING_BERUANG")));
        jumlah_sirip_hiu.setText(Integer.toString(toko_board.ambilStokProduk("SIRIP_HIU")));
        jumlah_jagung.setText(Integer.toString(toko_board.ambilStokProduk("JAGUNG")));
        jumlah_labu.setText(Integer.toString(toko_board.ambilStokProduk("LABU")));
        jumlah_daging_domba.setText(Integer.toString(toko_board.ambilStokProduk("DAGING_DOMBA")));
        jumlah_stroberi.setText(Integer.toString(toko_board.ambilStokProduk("STROBERI")));
        jumlah_telur.setText(Integer.toString(toko_board.ambilStokProduk("TELUR")));
        jumlah_susu.setText(Integer.toString(toko_board.ambilStokProduk("SUSU")));
        jumlah_daging_kuda.setText(Integer.toString(toko_board.ambilStokProduk("DAGING_KUDA")));
        sirip_hiu.setOnDragOver(event -> handleSellEventDragOver(event, sirip_hiu));
        sirip_hiu.setOnDragDropped(event -> handleSellEvent(event, "SIRIP_HIU"));
        sirip_hiu.setOnMouseClicked(event -> handleBuyEvent(event, "SIRIP_HIU"));
        susu.setOnDragOver(event -> handleSellEventDragOver(event, susu));
        susu.setOnDragDropped(event -> handleSellEvent(event, "SUSU"));
        susu.setOnMouseClicked(event -> handleBuyEvent(event, "SUSU"));
        daging_domba.setOnDragOver(event -> handleSellEventDragOver(event, daging_domba));
        daging_domba.setOnDragDropped(event -> handleSellEvent(event, "DAGING_DOMBA"));
        daging_domba.setOnMouseClicked(event -> handleBuyEvent(event, "DAGING_DOMBA"));
        daging_kuda.setOnDragOver(event -> handleSellEventDragOver(event, daging_kuda));
        daging_kuda.setOnDragDropped(event -> handleSellEvent(event, "DAGING_KUDA"));
        daging_kuda.setOnMouseClicked(event -> handleBuyEvent(event, "DAGING_KUDA"));
        telur.setOnDragOver(event -> handleSellEventDragOver(event, telur));
        telur.setOnDragDropped(event -> handleSellEvent(event, "TELUR"));
        telur.setOnMouseClicked(event -> handleBuyEvent(event, "TELUR"));
        daging_beruang.setOnDragOver(event -> handleSellEventDragOver(event, daging_beruang));
        daging_beruang.setOnDragDropped(event -> handleSellEvent(event, "DAGING_BERUANG"));
        daging_beruang.setOnMouseClicked(event -> handleBuyEvent(event, "DAGING_BERUANG"));
        jagung.setOnDragOver(event -> handleSellEventDragOver(event, jagung));
        jagung.setOnDragDropped(event -> handleSellEvent(event, "JAGUNG"));
        jagung.setOnMouseClicked(event -> handleBuyEvent(event, "JAGUNG"));
        labu.setOnDragOver(event -> handleSellEventDragOver(event, labu));
        labu.setOnDragDropped(event -> handleSellEvent(event, "LABU"));
        labu.setOnMouseClicked(event -> handleBuyEvent(event, "LABU"));
        stroberi.setOnDragOver(event -> handleSellEventDragOver(event, stroberi));
        stroberi.setOnDragDropped(event -> handleSellEvent(event, "STROBERI"));
        stroberi.setOnMouseClicked(event -> handleBuyEvent(event, "STROBERI"));

        toko_buka.setDisable(true);
        save.setDisable(true);
        ladang_sendiri.setDisable(true);
        ladang_lawan.setDisable(true);
        next_turn.setDisable(true);
    }

    // Menutup pane toko
    public void toko_to_main() {
        board.getChildren().remove(toko);
        toko_buka.setDisable(false);
        save.setDisable(false);
        ladang_sendiri.setDisable(false);
        ladang_lawan.setDisable(false);
        next_turn.setDisable(false);
    }

    // Tambahkan kartu ke shuffle field
    public void add_kartu_ke_shuffle_field() {

        shuffle_panel.getChildren().clear();
        Player a = main.getPlayernow();
        System.out.println(a.getName());
        a.deck_catat();
        int idx = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Card beruang = a.get_deck(idx);
                String kata = beruang.getName();
                VBox card_shuffle = new VBox();
                // System.out.println(beruang.getImgPath());
                Image image = new Image(this.getClass().getResource(beruang.getImgPath()).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(width);
                imageView.setFitHeight(height);
                
                Label label = new Label(kata);
                label.setStyle(font);

                card_shuffle.getChildren().add(label);
                card_shuffle.getChildren().add(imageView);
                card_shuffle.setStyle(style);
                card_shuffle.setId(Integer.toString(idx));
                card_shuffle.setOnMouseClicked(event -> remove_pane(a, card_shuffle, beruang));
                idx += 1;
                shuffle_panel.add(card_shuffle, i, j);
            }
        }
    }

    // Hapus pane
    public void remove_pane(Player player, VBox vBox, Card card) {
        if (player.getCard_in_one_turn() < 4 && player.get_deck_aktif_size() < 6) {
            String kata = player.getName();
            System.out.println("ini nama: " + kata);
            int id = card.getId();
            player.add_into_deck_aktiv(card);
            player.add_ciot();
            player.remove_deck(Integer.toString(id));
            shuffle_panel.getChildren().remove(vBox);
            player.print_deck_aktif();
        }
    }


    public void set_turn() {
        jumlah_turn.getChildren().addAll(new Label("Turn: " + main.getTotalturn()));
    }

    public void set_player() {
        Player a = main.getPlayernow();
        player_saat_ini.getChildren().addAll(new Label("Player saat ini: " + a.getName()));
    }

    public void saveTXT(ArrayList<String> gameState, ArrayList<String> player1, ArrayList<String> player2, String directory){
        TXTSaver saver = new TXTSaver();
        saver.saveFormattedData(directory, "gamestate.txt", gameState);
        saver.saveFormattedData(directory, "player1.txt", player1);
        saver.saveFormattedData(directory, "player2.txt", player2);

        System.out.println("aman");
    }

    public void loadTXT(String folder) {
        TXTLoader loader = new TXTLoader(folder);

        this.gameState = loader.tokenizeLines(loader.readFromFile("gamestate.txt"));
        this.player1 = loader.tokenizeLines(loader.readFromFile("player1.txt"));
        this.player2 = loader.tokenizeLines(loader.readFromFile("player2.txt"));


        Player p1 = main.getP1();
        Player p2 = main.getP2();

        ArrayList<String> game = (ArrayList<String>) gameState;
        ArrayList<String> a = (ArrayList<String>) player1;
        ArrayList<String> b = (ArrayList<String>) player2;

        game_state_load(game);
        p1.player_load(a);
        p2.player_load(b);
    }

    public void game_state_load(ArrayList<String> game) {
        int a = 0;
        main.set_totalturn(Integer.valueOf(game.get(a)));
        Toko toko_board = main.getToko();
        a += 1;
        int toko = Integer.valueOf(game.get(a));
        a += 1;
        for(int i = 0; i < toko; i++) {
            String kata = game.get(a);
            a += 1;
            int stok  = Integer.valueOf(game.get(a));
            a += 1;
            toko_board.setStokProduk(kata, stok);
        }
    }

    public void setBoard(Board board) {
        main = board;
    }

    // format namaProduk = SIRIP_HIU
    public void updateJumlah(String namaProduk) {
        switch (namaProduk) {
            case "SIRIP_HIU":
                jumlah_sirip_hiu.setText(Integer.toString(main.getToko().ambilStokProduk("SIRIP_HIU")));
                break;
            case "SUSU":
                jumlah_susu.setText(Integer.toString(main.getToko().ambilStokProduk("SUSU")));
                break;
            case "DAGING_DOMBA":
                jumlah_daging_domba.setText(Integer.toString(main.getToko().ambilStokProduk("DAGING_DOMBA")));
                break;
            case "DAGING_KUDA":
                jumlah_daging_kuda.setText(Integer.toString(main.getToko().ambilStokProduk("DAGING_KUDA")));
                break;
            case "TELUR":
                jumlah_telur.setText(Integer.toString(main.getToko().ambilStokProduk("TELUR")));
                break;
            case "DAGING_BERUANG":
                jumlah_daging_beruang.setText(Integer.toString(main.getToko().ambilStokProduk("DAGING_BERUANG")));
                break;
            case "JAGUNG":
                jumlah_jagung.setText(Integer.toString(main.getToko().ambilStokProduk("JAGUNG")));
                break;
            case "LABU":
                jumlah_labu.setText(Integer.toString(main.getToko().ambilStokProduk("LABU")));
                break;
            case "STROBERI":
                jumlah_stroberi.setText(Integer.toString(main.getToko().ambilStokProduk("STROBERI")));
                break;
        }
    }

    public void updateUangPlayer() {
        if (main.getTurn() == 1) {
            uang_player1.setText(Integer.toString(main.getPlayernow().getCoin()));
        } else {
            uang_player2.setText(Integer.toString(main.getPlayernow().getCoin()));
        }
    }

    public void handleSellEventDragOver(DragEvent event, AnchorPane anchorPane) {
        if (event.getGestureSource() != anchorPane && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    // format nama produk: SIRIP_HIU
    public void handleSellEvent(DragEvent event, String namaProduk) {
        Player currentPlayer = main.getPlayernow();
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (!db.hasString() || !db.getString().equals("pane")) {
            return;
        }
        Pane draggedPane = (Pane) event.getGestureSource();
        draggedPane.setStyle(style);

        // Cek apakah Pane berasal dari deck_aktif
        if (!deck_aktif.getChildren().contains(draggedPane)) {
            return;
        }

        // Cek kartu yang di-drop
        Card droppedCard = currentPlayer.get_card_aktif(currentPlayer.get_card_aktif_idx(draggedPane.getId()));
        boolean match = switch (namaProduk) {
            case "SIRIP_HIU" -> droppedCard instanceof SiripHiu;
            case "SUSU" -> droppedCard instanceof Susu;
            case "DAGING_DOMBA" -> droppedCard instanceof DagingDomba;
            case "DAGING_KUDA" -> droppedCard instanceof DagingKuda;
            case "TELUR" -> droppedCard instanceof Telur;
            case "DAGING_BERUANG" -> droppedCard instanceof DagingBeruang;
            case "JAGUNG" -> droppedCard instanceof Jagung;
            case "LABU" -> droppedCard instanceof Labu;
            case "STROBERI" -> droppedCard instanceof Stroberi;
            default -> false;
        };

        if (!match) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Woy!");
            alert.setHeaderText("Baca Nih!!!");
            alert.setContentText("Kartunya bukan " + namaProduk.toLowerCase().replace("_", " "));
            // Show the alert and wait for the user to close it
            alert.showAndWait();
            return;
        }

        deck_aktif.getChildren().remove(draggedPane);
        currentPlayer.drop_deck_aktif(droppedCard);
        currentPlayer.jual(main.getToko(), namaProduk);
        updateJumlah(namaProduk);
        updateUangPlayer();
        success = true;

        event.setDropCompleted(success);
        event.consume();
    }

    // format nama produk: SIRIP_HIU
    public void handleBuyEvent(MouseEvent event, String namaProduk) {
        if (event.getClickCount() != 2) {
            return;
        }

        Player currentPlayer = main.getPlayernow();

        if (currentPlayer.get_deck_aktif_size() >= 6) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Woy!");
            alert.setHeaderText("Baca Nih!!!");
            alert.setContentText("Deck aktif penuh");
            // Show the alert and wait for the user to close it
            alert.showAndWait();
            return;
        }

        try {
            currentPlayer.beli(main.getToko(), namaProduk);
        } catch (IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Woy!");
            alert.setHeaderText("Baca Nih!!!");
            alert.setContentText(e.getMessage());
            // Show the alert and wait for the user to close it
            alert.showAndWait();
        }

        updateJumlah(namaProduk);
        Card newCard = switch (namaProduk) {
            case "SIRIP_HIU" -> new SiripHiu();
            case "SUSU" -> new Susu();
            case "DAGING_DOMBA" -> new DagingDomba();
            case "DAGING_KUDA" -> new DagingKuda();
            case "TELUR" -> new Telur();
            case "DAGING_BERUANG" -> new DagingBeruang();
            case "JAGUNG" -> new Jagung();
            case "LABU" -> new Labu();
            case "STROBERI" -> new Stroberi();
            default -> null;
        };

        currentPlayer.add_into_deck_aktiv(Objects.requireNonNull(newCard));
        updateUangPlayer();
        add_to_deck_aktif();
    }

    public void change_format() {
        format_save.getItems().addAll("TXT","XAML","JSON");
        format_load.getItems().addAll("TXT","XAML","JSON");
    }
}

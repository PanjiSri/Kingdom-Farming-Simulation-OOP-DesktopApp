package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;
import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Hewan.Hewan;
import org.example.card.Item.Item;
import org.example.card.Produk.*;
import org.example.card.Tumbuhan.Tumbuhan;

import java.util.ArrayList;
import java.util.Objects;

public class MainController {

    @FXML
    private GridPane ladang, shuffle_panel, deck_aktif;

    @FXML
    private AnchorPane info_pane;

    @FXML
    private Pane pane_ladang, ambil_kartu, jumlah_turn, player_saat_ini, halo;

    @FXML
    private Button next_turn, shuffle_card, close_button, ladang_lawan, ladang_sendiri, panen, tutup_info, save;

    @FXML
    private StackPane board;

    @FXML
    private VBox info_hewan;

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

        Circle circle = new Circle(50, 50, 50);
        circle.setCenterX(20);

        Button button = new Button("Click me!");
        button.setOnAction(e -> System.out.println("Hello, World!"));

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
                    System.out.println("Masalah");
                    deck_aktif.getChildren().remove(draggedPane);
                    System.out.println(draggedPane.getId());
                    String id = draggedPane.getId();
                    int idx_card_deck_aktif = a.get_card_aktif_idx(id);
                    System.out.println("Dallas");
                    a.add_in_lahan(row, col, a.get_card_aktif(idx_card_deck_aktif));
                    a.drop_deck_aktif(a.get_card_aktif(idx_card_deck_aktif));
                    System.out.println("Ini kartu: " + draggedPane.getId());
                    System.out.println();

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
                    System.out.println("Dallas");
                    a.add_in_lahan(row, col, a.get_card_ladang(idx_lahan.get(0), idx_lahan.get(1)));
                    a.drop_ladang(idx_lahan.get(0), idx_lahan.get(1));
                    System.out.println("Ini kartu: " + draggedPane.getId());
                    System.out.println();
                    // Tambahkan ke ladang
                    a.print_lahan();
                    success = true;
                    ladang.getChildren().remove(draggedPane);
                    ladang.add(draggedPane, col, row);
                }

                // Hitung baris dan kolom baru berdasarkan posisi drop

                // Pastikan tidak ada elemen pada posisi tersebut
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
                    System.out.println(a.get_card_ladang(i, j));
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
                    pane.setId(Integer.toString(id));
                    ladang.add(pane, j, i);
                }
            }
        }
    }

    // Set fungsi pda button
    public void initialize_click() {
        next_turn.setOnAction(e -> {
            Player a = main.getPlayernow();
            a.add_umur_tumbuhan();
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
                            }
                            // Hitung baris dan kolom baru berdasarkan posisi drop
                            int col = (int) (event.getX() / (ladang.getWidth() / ladang.getColumnCount()));
                            int row = (int) (event.getY() / (ladang.getHeight() / ladang.getRowCount()));

                            // Pastikan tidak ada elemen pada posisi tersebut
                            System.out.println(draggedPane.getId());
                            String id_dragged = draggedPane.getId();
                            int idx_card_deck_aktif = a.get_card_aktif_idx(id_dragged);
                            Card card_dragged = a.get_card_aktif(idx_card_deck_aktif);
                            if (card_dragged instanceof Item) {
                                ((Item) card_dragged).aksi((BisaPanen) card);
                            }

                            // Tambahkan ke ladang
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

    // Panen
//    public void panen() {
//        Player a = main.getPlayernow();
//        a.panen();
//        change_to_main();
    // }

    // Ubah ke pane shuffle
    public void change_to_shuffle() {
        main.add_turn();
        Player a = main.getPlayernow();
        a.reset_ciot();
        board.getChildren().clear();
        add_kartu_ke_shuffle_field();
        board.getChildren().setAll(pane_ladang, ambil_kartu);
//        System.out.println(board.getChildren().size());
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
    }

    // Membuka pane toko
    public void main_to_toko() {
        board.getChildren().add(toko);

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
        if (db.hasString() && db.getString().equals("pane")) {
            Pane draggedPane = (Pane) event.getGestureSource();
            draggedPane.setStyle(style);

            // Hapus dari deck_aktif hanya jika Pane berasal dari deck_aktif
            if (deck_aktif.getChildren().contains(draggedPane)) {
                deck_aktif.getChildren().remove(draggedPane);
                String id = draggedPane.getId();
                System.out.println("Ini kartu: " + id);
                int idx_card_deck_aktif = currentPlayer.get_card_aktif_idx(id);
                currentPlayer.drop_deck_aktif(currentPlayer.get_card_aktif(idx_card_deck_aktif));
                currentPlayer.jual(main.getToko(), namaProduk);
                updateJumlah(namaProduk);
                updateUangPlayer();
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    // format nama produk: SIRIP_HIU
    public void handleBuyEvent(MouseEvent event, String namaProduk) {
        if (event.getClickCount() != 2) {
            return;
        }
        Player currentPlayer = main.getPlayernow();
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
    }
}

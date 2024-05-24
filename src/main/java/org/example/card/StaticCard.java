package org.example.card;

public class StaticCard {
    static public String[] cardNames = {
        "org.example.card.Hewan.Ayam",
        // "org.example.card.Hewan.Beruang",
        "org.example.card.Hewan.Domba",
        "org.example.card.Hewan.HiuDarat",
        "org.example.card.Hewan.Kuda",
        "org.example.card.Hewan.Sapi",
        "org.example.card.Tumbuhan.BijiJagung",
        "org.example.card.Tumbuhan.BijiLabu",
        "org.example.card.Tumbuhan.BijiStroberi",
        "org.example.card.Item.Accelerate",
        "org.example.card.Item.Delay",
        "org.example.card.Item.Destroy",
        "org.example.card.Item.InstantHarvest",
        "org.example.card.Item.Protect",
        "org.example.card.Item.Trap",
        // "DagingBeruang",
        // "DagingDomba",
        // "DagingKuda",
        // "Jagung",
        // "Labu",
        // "SiripHiu",
        // "Stroberi",
        // "Susu",
        // "Telur",
    };

    public static String getRandomCardName() {
        return cardNames[(int) (Math.random() * cardNames.length)];
    }
}

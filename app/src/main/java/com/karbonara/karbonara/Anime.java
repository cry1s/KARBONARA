package com.karbonara.karbonara;


public class Anime {
    public String poster;
    public String title;
    public int readyEpisodes;
    public int allEpisodes;
    public String description;
    public int id;
    public String titleRus;
    public String title2RUS;
    public String listMembers;
    public String readyStatus = "";
    public String statusSerii;

    public Anime(int id, String title, String titleRus, String title2RUS, int readyEpisodes,
                 int allEpisodes, String descriptionRus, String listMember, String poster,
                 String statusSerii) {
        this.id = id;
        this.title = title;
        this.titleRus = titleRus;
        if (!title2RUS.equals("null")) this.title2RUS = title2RUS;
        else this.title2RUS = null;
        this.readyEpisodes = readyEpisodes;
        this.allEpisodes = allEpisodes;
        if (readyEpisodes != allEpisodes) this.readyStatus = "[" + readyEpisodes + "/" + allEpisodes + "]";
        else this.readyStatus = "Релиз";
        this.description = descriptionRus;
        this.listMembers = listMember;
        this.poster = poster;
        switch (statusSerii) {
            case "P":
                this.statusSerii = "";
                break;
            case "О":
                this.statusSerii = "На озвучке";
                break;
            case "ГT":
                this.statusSerii = "На тайминге";
                break;
            case "ГВ":
                this.statusSerii = "Выкладывается!";
                break;
            default:
                this.statusSerii = "Неизвестно O_o";
                break;
        }
    }

    //for test
    public Anime() {
        this.id = 1;
        this.title = "title";
        this.titleRus = "titleRus";
        //if (!title2RUS.equals("null"))
        this.title2RUS = "title2RUS";
        //else this.title2RUS = null;
        this.readyEpisodes = 1;
        this.allEpisodes = 12;
        this.readyStatus = "[" + readyEpisodes + "/" + allEpisodes + "]";
        this.description = "descriptionRus";
        this.listMembers = "AUTHORS";
    }

    public void updateReadyStatus() {
        this.readyStatus = "[" + readyEpisodes + "/" + allEpisodes + "]";
    }

    public String getTitle() {
        return titleRus;
    }
}

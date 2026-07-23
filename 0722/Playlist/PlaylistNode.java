package Playlist;

public class PlaylistNode {
    String songId;
    String songName;
    PlaylistNode next;

    public PlaylistNode(String songId, String songName) {
        this.songId = songId;
        this.songName = songName;
        this.next = null;
    }
}
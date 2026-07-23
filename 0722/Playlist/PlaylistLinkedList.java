package Playlist;

public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        this.head = null;
    }

    public boolean addLast(String songId, String songName) {
        if (contains(songId)) {
            System.out.println("[新增失敗] 歌曲代碼已重複: " + songId);
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songId, songName);
        if (head == null) {
            head = newNode;
            System.out.println("[新增成功] " + songId + " - " + songName);
            return true;
        }

        PlaylistNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println("[新增成功] " + songId + " - " + songName);
        return true;
    }

    public boolean contains(String songId) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songId.equals(songId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public PlaylistNode searchById(String songId) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songId.equals(songId)) {
                System.out.println("[搜尋成功] 代碼: " + songId + ", 歌名: " + current.songName);
                return current;
            }
            current = current.next;
        }
        System.out.println("[搜尋失敗] 找不到代碼為 " + songId + " 的歌曲");
        return null;
    }

    public boolean removeById(String songId) {
        if (head == null) {
            System.out.println("[刪除失敗] 播放清單為空。");
            return false;
        }

        if (head.songId.equals(songId)) {
            System.out.println("[刪除成功] 已移除第一首歌: " + head.songId + " - " + head.songName);
            head = head.next;
            return true;
        }

        PlaylistNode current = head;
        while (current.next != null && !current.next.songId.equals(songId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到代碼為 " + songId + " 的歌曲");
            return false;
        }

        System.out.println("[刪除成功] 已移除歌曲: " + current.next.songId + " - " + current.next.songName);
        current.next = current.next.next;
        return true;
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放順序: (清單目前為空)");
            return;
        }

        PlaylistNode current = head;
        System.out.println("--- 完整播放順序 ---");
        int index = 1;
        while (current != null) {
            System.out.println(index + ". [" + current.songId + "] " + current.songName);
            current = current.next;
            index++;
        }
        System.out.println("--------------------");
    }
}
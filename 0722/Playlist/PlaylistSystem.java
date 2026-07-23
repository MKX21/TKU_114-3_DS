package Playlist;

public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 1. 測試尾端新增與代碼重複檢查 ===");
        playlist.addLast("S001", "Song A");
        playlist.addLast("S002", "Song B");
        playlist.addLast("S003", "Song C");
        playlist.addLast("S004", "Song D");
        playlist.addLast("S002", "Duplicate Song"); // 重複 ID 測試

        playlist.printPlaylist();

        System.out.println("\n=== 2. 測試代碼搜尋 ===");
        playlist.searchById("S003");
        playlist.searchById("S099"); // 找不到測試

        System.out.println("\n=== 3. 測試刪除第一首歌曲 (S001) ===");
        playlist.removeById("S001");
        playlist.printPlaylist();

        System.out.println("\n=== 4. 測試刪除最後一首歌曲 (S004) ===");
        playlist.removeById("S004");
        playlist.printPlaylist();

        System.out.println("\n=== 5. 測試刪除不存在的歌曲 (S099) ===");
        playlist.removeById("S099");

        System.out.println("\n=== 6. 清空清單並測試邊界條件 ===");
        playlist.removeById("S002");
        playlist.removeById("S003");
        playlist.printPlaylist();
        playlist.removeById("S001"); // 從空清單刪除
    }
}
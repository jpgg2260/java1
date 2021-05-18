package Java1開発演習;

import java.util.Scanner;

public class ポケモン {

	public static void main(String[] args) {

		/*変数宣言*/
		int[][] status = { { 20, 10, 9 }, { 20, 11, 9 } };
		//{{キモトのHP,こうげき,ぼうぎょ},{アツァモのHP,こうげき,ぼうぎょ}

		Scanner scanner = new Scanner(System.in);
		while (true) {
			//１ターン
			export_status(status); //HP出力
			while (true) {
				//プレイヤー行動選択
				System.out.println("どうする？");
				System.out.println("▷こうげき　もちもの");
				System.out.println("　いれかえ　にげる");
				String action = scanner.next();
				if (action.equals("こうげき") || action.equals("k")) {
					break;
				} else
					System.out.println("こうげき　いがい　できないよ\n");
			}

			System.out.println("わざ　は　どうする？");
			System.out.println("▷はたく　にらみつける");
			String move = scanner.next();
			while (true) {
				if (move.equals("はたく") || move.equals("h")) {
					attack("キモト", status);
					break;
				} else if (move.equals("にらみつける") || move.equals("n")) {
					leer(status);
					break;
				} else
					System.out.println("そんな　わざ　ないよ\n");
			}
			//			System.out.println(status[1][0]); //テスト
			if (status[1][0] < 1) {
				System.out.println("キモトが勝った！！！");
				break;
			}

			System.out.println("アツァモのターンだ！！！");
			if (Math.random() < 0.5)
				attack("アツァモ", status);
			else {
				growl(status);
			}

			if (status[0][0] < 1) {
				System.out.println("負けた。");
				break;
			}
		}
		scanner.close();
	}

	public static void export_status(int[][] status) {
		//状態を出力
		System.out.println("-----------------------------------");
		System.out.println("🐤アツァモ　　HP:" + status[1][0]);
		System.out.println();
		System.out.println();
		System.out.println("　　　　　　🐸キモト　　HP:" + status[0][0]);
		System.out.println("-----------------------------------");
	}

	public static void attack(String name, int[][] status) {
		//こうげき（ひっかく、はたく）
		if (name.equals("キモト")) {
			System.out.println(name + "　は　はたいた！！！");
			consoleStop();
			int damege = ((5 * 2 / 5 + 2) * 30 * status[0][1] / status[1][2]) / 50 + 2;
			System.out.println(damege + "のダメージ！！！\n");
			consoleStop();
			status[1][0] -= damege;
		}

		else {
			System.out.println(name + "　は　ひっかいた！！！");
			consoleStop();
			int damege = ((5 * 2 / 5 + 2) * 30 * status[1][1] / status[0][2]) / 50 + 2;
			if (Math.random() < 0.3) {
				System.out.println("きゅうしょ　に　あたった！！！");
				damege *= 2;
			}
			System.out.println(damege + "のダメージ！！！\n");
			consoleStop();
			status[0][0] -= damege;
		}
	}

	public static void leer(int[][] status) {
		//にらみつける（防御を１下げる）
		System.out.println("キモト　は　にらみつけた！！！");
		consoleStop();
		if (status[1][2] < 5) {
			System.out.println("アツァモ　の　ぼうぎょ　は　もうさがらない！！！");
			consoleStop();
		} else {
			System.out.println("アツァモ　の　ぼうぎょ　が　さがった！！！\n");
			consoleStop();
			//			System.out.println("ぼうぎょ：" + status[1][2]); //テスト
			status[1][2] *= 0.7;
			//			System.out.println("ぼうぎょ：" + status[1][2]); //テスト
		}
	}

	public static void growl(int[][] status) {
		//なきごえ（攻撃を１下げる）
		System.out.println("アツァモ　は　ないた！！！");
		consoleStop();
		if (status[0][1] < 5) {
			System.out.println("キモト　の　こうげき　は　もうさがらない！！！");
			consoleStop();
		} else {
			System.out.println("キモト　の　こうげき　が　さがった！！！\n");
			consoleStop();
			status[0][1] *= 0.7;
		}
	}

	static void consoleStop() {
		try {
			Thread.sleep(1000); // 2秒(1000ミリ秒)間だけ処理を止める
		} catch (InterruptedException e) {
		}
	}

}

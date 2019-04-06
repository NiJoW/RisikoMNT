package risiko.local.valueobjects;

public class Welt {
		
	boolean [][] pBeziehungen;
	
	public Welt() {
			erstellePBeziehungen();	
	}

	public boolean getBeziehungen(int from, int to) {
		return pBeziehungen[from][to];		
	}
	
	private void erstellePBeziehungen() {
		pBeziehungen = new boolean [42][42];
		for(int i = 0; i < pBeziehungen.length; i++) {
			for(int j = 0; j < pBeziehungen[i].length; j++) {

				pBeziehungen[i][j] = false;
			}
		}
		pBeziehungen[0][3] = true;
		pBeziehungen[0][4] = true;
		pBeziehungen[0][26] = true;
		pBeziehungen[0][13] = true;
		pBeziehungen[1][3] = true;
		pBeziehungen[1][4] = true;
		pBeziehungen[1][5] = true;
		pBeziehungen[2][4] = true;
		pBeziehungen[2][5] = true;
		pBeziehungen[3][0] = true;
		pBeziehungen[3][4] = true;
		pBeziehungen[3][1] = true;
		pBeziehungen[3][39] = true;
		pBeziehungen[3][26] = true;
		pBeziehungen[3][28] = true;
		pBeziehungen[4][0] = true;
		pBeziehungen[4][3] = true;
		pBeziehungen[4][1] = true;
		pBeziehungen[4][5] = true;
		pBeziehungen[4][13] = true;
		pBeziehungen[5][1] = true;
		pBeziehungen[5][4] = true;
		pBeziehungen[5][2] = true;
		
		pBeziehungen[6][27] = true;
		pBeziehungen[6][17] = true;
		pBeziehungen[6][7] = true;
		pBeziehungen[6][8] = true;
		pBeziehungen[6][13] = true;
		pBeziehungen[7][6] = true;
		pBeziehungen[7][17] = true;
		pBeziehungen[7][16] = true;
		pBeziehungen[7][14] = true;
		pBeziehungen[7][15] = true;
		pBeziehungen[7][8] = true;
		pBeziehungen[8][6] = true;
		pBeziehungen[8][7] = true;
		pBeziehungen[8][15] = true;
		pBeziehungen[8][13] = true;
		pBeziehungen[9][16] = true;
		pBeziehungen[9][10] = true;
		pBeziehungen[9][12] = true;
		pBeziehungen[9][14] = true;
		pBeziehungen[10][9] = true;
		pBeziehungen[10][16] = true;
		pBeziehungen[10][12] = true;
		pBeziehungen[11][14] = true;
		pBeziehungen[11][12] = true;
		pBeziehungen[12][11] = true;
		pBeziehungen[12][14] = true;
		pBeziehungen[12][9] = true;
		pBeziehungen[12][10] = true;
		pBeziehungen[12][29] = true;
		pBeziehungen[13][6] = true;
		pBeziehungen[13][8] = true;
		pBeziehungen[13][4] = true;
		pBeziehungen[13][0] = true;
		pBeziehungen[13][26] = true;
		pBeziehungen[13][27] = true;
		pBeziehungen[14][16] = true;
		pBeziehungen[14][9] = true;
		pBeziehungen[14][12] = true;
		pBeziehungen[14][11] = true;
		pBeziehungen[14][7] = true;
		pBeziehungen[15][8] = true;
		pBeziehungen[15][7] = true;
		pBeziehungen[15][18] = true;
		pBeziehungen[16][17] = true;
		pBeziehungen[16][10] = true;
		pBeziehungen[16][9] = true;
		pBeziehungen[16][14] = true;
		pBeziehungen[16][7] = true;
		pBeziehungen[17][16] = true;
		pBeziehungen[17][7] = true;
		pBeziehungen[17][6] = true;
		pBeziehungen[17][27] = true;
		
		pBeziehungen[18][15] = true;
		pBeziehungen[18][19] = true;
		pBeziehungen[18][21] = true;
		pBeziehungen[19][18] = true;
		pBeziehungen[19][20] = true;
		pBeziehungen[19][21] = true;
		pBeziehungen[20][19] = true;
		pBeziehungen[20][21] = true;
		pBeziehungen[21][20] = true;
		pBeziehungen[21][19] = true;
		pBeziehungen[21][18] = true;
		
		pBeziehungen[22][23] = true;
		pBeziehungen[22][25] = true;
		pBeziehungen[22][24] = true;
		pBeziehungen[22][28] = true;
		pBeziehungen[23][22] = true;
		pBeziehungen[23][25] = true;
		pBeziehungen[23][31] = true;
		pBeziehungen[24][25] = true;
		pBeziehungen[24][27] = true;
		pBeziehungen[24][26] = true;
		pBeziehungen[24][28] = true;
		pBeziehungen[24][22] = true;
		pBeziehungen[25][23] = true;
		pBeziehungen[25][27] = true;
		pBeziehungen[25][24] = true;
		pBeziehungen[25][22] = true;
		pBeziehungen[26][27] = true;
		pBeziehungen[26][13] = true;
		pBeziehungen[26][0] = true;
		pBeziehungen[26][3] = true;
		pBeziehungen[26][28] = true;
		pBeziehungen[26][24] = true;
		pBeziehungen[27][17] = true;
		pBeziehungen[27][6] = true;
		pBeziehungen[27][13] = true;
		pBeziehungen[27][26] = true;
		pBeziehungen[27][24] = true;
		pBeziehungen[27][25] = true;
		pBeziehungen[28][22] = true;
		pBeziehungen[28][24] = true;
		pBeziehungen[28][26] = true;
		pBeziehungen[28][3] = true;
		pBeziehungen[29][33] = true;
		pBeziehungen[29][30] = true;
		pBeziehungen[29][12] = true;
		pBeziehungen[30][29] = true;
		pBeziehungen[30][33] = true;
		pBeziehungen[30][34] = true;
		pBeziehungen[30][37] = true;
		pBeziehungen[31][23] = true;
		pBeziehungen[31][36] = true;
		pBeziehungen[31][34] = true;
		pBeziehungen[31][33] = true;
		pBeziehungen[32][37] = true;
		pBeziehungen[32][35] = true;
		pBeziehungen[32][41] = true;
		pBeziehungen[33][29] = true;
		pBeziehungen[33][30] = true;
		pBeziehungen[33][34] = true;
		pBeziehungen[33][31] = true;
		pBeziehungen[34][31] = true;
		pBeziehungen[34][36] = true;
		pBeziehungen[34][35] = true;
		pBeziehungen[34][37] = true;
		pBeziehungen[34][30] = true;
		pBeziehungen[34][33] = true;
		pBeziehungen[35][34] = true;
		pBeziehungen[35][36] = true;
		pBeziehungen[35][32] = true;
		pBeziehungen[35][37] = true;
		pBeziehungen[36][31] = true;
		pBeziehungen[36][35] = true;
		pBeziehungen[36][34] = true;
		pBeziehungen[37][30] = true;
		pBeziehungen[37][34] = true;
		pBeziehungen[37][35] = true;
		pBeziehungen[37][32] = true;
		
		pBeziehungen[38][39] = true;
		pBeziehungen[38][40] = true;
		pBeziehungen[39][41] = true;
		pBeziehungen[39][40] = true;
		pBeziehungen[39][3] = true;
		pBeziehungen[39][38] = true;
		pBeziehungen[40][41] = true;
		pBeziehungen[40][39] = true;
		pBeziehungen[40][38] = true;
		pBeziehungen[41][39] = true;
		pBeziehungen[41][40] = true;
		pBeziehungen[41][32] = true;	
	}

	public boolean[][] getBeziehungsMatrix() {
		return pBeziehungen;
	}

	
}

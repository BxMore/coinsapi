package de.supremedev.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsManager {
	public static int getCoins(String uuid) {
		try {
			PreparedStatement st = MySQL.con.prepareStatement("SELECT Coins FROM Coins WHERE UUID = ?");
			st.setString(1, uuid);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt("Coins");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public static void setCoins(String uuid, int Coins) {
		if (getCoins(uuid) == 0) {
			try {
				PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO Coins (UUID,Coins) VALUES (?,?)");
				st.setString(1, uuid);
				st.setInt(2, Coins);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} else {
			PreparedStatement st = null;
			try {
				st = MySQL.con.prepareStatement("UPDATE Coins SET Coins = ? WHERE UUID = ?");
				st.setString(2, uuid);
				st.setInt(1, Coins);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} 
	}
	
	public static void addCoins(String uuid, int Coins) {
		int current = getCoins(uuid);
		setCoins(uuid, Coins + current);
	}
	
	
	public static void removeCoins(String uuid, int Coins) {
		setCoins(uuid, getCoins(uuid) - Coins);
	}
}
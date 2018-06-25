package com.qait.automation.snl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

public class BoardObject {
	Board test;
	 JSONObject player;
	 JSONObject data;
	
	public BoardObject() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		test=new Board();
		//test=new Board(test.getUUID());
	}
	
	
	public void moreThanFourPlayers(String user1,String user2,String user3,String user4,String user5) throws 
	FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
	GameInProgressException, MaxPlayersReachedExeption, IOException {
		test.registerPlayer(user1);
		test.registerPlayer(user2);
		test.registerPlayer(user3);
		test.registerPlayer(user4);
		test.registerPlayer(user5);
		
	}
	
	public void PlayerExists(String user1,String user2,String user3,String user4) throws
	PlayerExistsException, FileNotFoundException, UnsupportedEncodingException,
	GameInProgressException, MaxPlayersReachedExeption, IOException {
		test.registerPlayer(user1);
		test.registerPlayer(user2);
		test.registerPlayer(user3);
		test.registerPlayer(user4);
	}
	
	public void GameInprogress(String user1,String user2,String user3) throws FileNotFoundException, UnsupportedEncodingException,
	IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		test.registerPlayer(user1);
		test.registerPlayer(user2);
		data=test.getData();
		for(int i = 0; i < data.getJSONArray("players").length(); ){
			player= data.getJSONArray("players").getJSONObject(i);
			UUID id=(UUID) player.get("uuid");
			test.rollDice(id);
			break;
		 }		
		test.registerPlayer(user3);
	}
	
	public void deletePlayerIfNotPresent() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		UUID uuid=UUID.randomUUID();
		test.deletePlayer(uuid);
		
	}
	public void deletePlayerIfPresent(String user1,String user2,String user3) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException, InvalidTurnException {
		Boolean status=false;
		test.registerPlayer(user1);
		test.registerPlayer(user2);
		test.registerPlayer("rahul");
		data=test.getData();
		for(int i = 0; i < data.getJSONArray("players").length(); i++){
			player= data.getJSONArray("players").getJSONObject(i);
			String name=player.getString("name");
			UUID id= (UUID) player.get("uuid");
			System.out.println(name);
			System.out.println(id);
		    if(name.equals(user3))
			    {
					test.deletePlayer(id);		
					status=true;
					break;
				}
			}
		for(int i = 0; i < data.getJSONArray("players").length(); i++){
			
			player= data.getJSONArray("players").getJSONObject(i);
			String name=player.getString("name");
			if(name.equals(user3)) {
				System.out.println("test failed user not deleted");
				}
			}
		Assert.assertTrue(status);
		
	}


	public void invalidTurnException(String user1, String user2, String user3) throws 
	FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
	GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		test.registerPlayer(user1);
		test.registerPlayer(user2);
		test.registerPlayer(user3);
		data=test.getData();
			player=data.getJSONArray("players").getJSONObject(2);
				test.rollDice((UUID) player.get("uuid"));		
	}
	
	
	public void Player_Moved_to_New_Position(String user1, String user2, String user3)
	throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
	GameInProgressException, MaxPlayersReachedExeption, IOException, JSONException, InvalidTurnException {
		test.registerPlayer(user1);
		test.registerPlayer(user2);
		test.registerPlayer(user3);
		data=test.getData();
			player=data.getJSONArray("players").getJSONObject(0);
			int	currentPosition=player.getInt("position");
			System.out.println(currentPosition);
			test.rollDice((UUID)player.get("uuid"));
			int newPosition=player.getInt("position");
			System.out.println(newPosition);
			Assert.assertNotEquals(currentPosition, newPosition);
	}
	
	public void Player_bit_By_Snake(String user1) 
			throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, IOException, JSONException, InvalidTurnException {
		test.registerPlayer(user1);
		data=test.getData();
		player=data.getJSONArray("players").getJSONObject(0);
		int currentPosition=player.getInt("position");
		test.rollDice((UUID)player.get("uuid"));
		currentPosition=player.getInt("position");
		if(currentPosition==99||currentPosition==93||currentPosition==55||currentPosition==70||currentPosition==23)
		{
			
		}
	}
}
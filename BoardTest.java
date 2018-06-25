package com.qait.automation.snl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardTest {
	Board test;
	BoardObject object;
	
	@BeforeMethod
	public void gettingUUID() throws IOException {
		object=new BoardObject();
	}
	
	@Test(expectedExceptions=MaxPlayersReachedExeption.class)
	public void Step_01_Max_Players_Reached_Exeption_Check() throws 
	FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
	GameInProgressException, MaxPlayersReachedExeption, IOException{
		
		object.moreThanFourPlayers("Gaurav","aditya","karan","rahul","himanashu");
		
	}
	
	@Test(expectedExceptions=PlayerExistsException.class)
	public void Step_02_Player_Exists_Exception_Check() throws
	PlayerExistsException, FileNotFoundException, UnsupportedEncodingException,
	GameInProgressException, MaxPlayersReachedExeption, IOException {
		
		object.PlayerExists("gaurav","aditya","karan","gaurav");
		
	}
	
	@Test(expectedExceptions=GameInProgressException.class)
	public void Step_03_Game_In_Progress_Exception_Check() throws FileNotFoundException, 
	UnsupportedEncodingException,IOException, PlayerExistsException, GameInProgressException,
	MaxPlayersReachedExeption, InvalidTurnException {
		
		object.GameInprogress("gaurav","aditya","INVALID PLAYER");
		
	}
	@Test
	public void Step_04_delete_Player_If_Player_Present() 
			throws FileNotFoundException, UnsupportedEncodingException, 
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, 
			IOException, NoUserWithSuchUUIDException, InvalidTurnException {
		
		object.deletePlayerIfPresent("Gaurav", "Aditya","Gaurav");
		
	}
	@Test(expectedExceptions=NoUserWithSuchUUIDException.class)
	public void Step_05_delete_Player_If_Player_Not_Present() 
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		
		object.deletePlayerIfNotPresent();
		
	}
	@Test(expectedExceptions=InvalidTurnException.class)
	public void Step_06_Checking_Invalid_Turn_Exception() 
			throws FileNotFoundException, IOException, InvalidTurnException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, NoUserWithSuchUUIDException {
		//object.deletePlayerIfNotPresent();
		object.invalidTurnException("Gaurav","Aditya","Himanshu");
		
	}
	@Test
	public void Step_07_Check_if_player_Moved_To_New_Position() 
			throws FileNotFoundException, UnsupportedEncodingException, JSONException, 
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, 
			IOException, InvalidTurnException {
		
		object.Player_Moved_to_New_Position("Gaurav","Aditya","Himanshu");
		
	}
	
	
	
}

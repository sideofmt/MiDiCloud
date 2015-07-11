package model;

import java.util.ArrayList;
import java.util.List;

public class MidifileManager {
	public List<Midifile> search(String title) {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		MidifileDAO midifileDAO= new MidifileDAO();
		
		midifiles.addAll(midifileDAO.getMidiList(title));
		
		return midifiles;
	}
	public List<Midifile> search(int userID) {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		MidifileDAO midifileDAO= new MidifileDAO();
		
		midifiles.addAll(midifileDAO.getMidiList(userID));
		
		return midifiles;
	}
	public List<Midifile> getRanking() {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		MidifileDAO midifileDAO= new MidifileDAO();
		
		midifiles.addAll(midifileDAO.getMidiRankingList());
		
		return midifiles;
	}
	public List<Midifile> getArrival() {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		MidifileDAO midifileDAO= new MidifileDAO();
		
		midifiles.addAll(midifileDAO.getMidiArrivalList());
		
		return midifiles;
	}
}

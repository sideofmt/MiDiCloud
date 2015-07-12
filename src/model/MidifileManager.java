package model;

import java.util.ArrayList;
import java.util.List;

public class MidifileManager {
	public void add(Midifile midifile){
		MidifileDAO midifileDAO = new MidifileDAO();

		midifileDAO.addMidifile(midifile);
	}

	public List<Midifile> searchList(String title) {
		ArrayList<Midifile> midifiles = new ArrayList<Midifile>();
		MidifileDAO midifileDAO= new MidifileDAO();

		midifiles.addAll(midifileDAO.getMidiList(title));

		return midifiles;
	}
	public List<Midifile> searchList(int userID) {
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
	public void update(Midifile midifile) {
		MidifileDAO midifileDAO = new MidifileDAO();

		midifileDAO.updateMidifile(midifile);
	}
	public void delete(int midiID) {
		MidifileDAO midifileDAO = new MidifileDAO();

		midifileDAO.deleteMidifile(midiID);
	}
	public Midifile search(int midiID) {
		Midifile midifile = new Midifile();
		MidifileDAO midifileDAO= new MidifileDAO();

		midifile = midifileDAO.getMidifile(midiID);

		return midifile;
	}
}

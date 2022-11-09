package com.mistershorr.soundboard

import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import com.mistershorr.soundboard.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var soundPool : SoundPool
    lateinit var playSong : Button

    var aNote = 0
    var bbNote = 0
    var bNote = 0
    var cNote = 0
    var csNote = 0
    var dNote = 0
    var dsNote = 0
    var eNote = 0
    var fNote = 0
    var fsNote = 0
    var gNote = 0
    var gsNote = 0
    var noteMap = HashMap<String, Int>()
    var wait = 200L

    //instance variable for the view binding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // define the binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeSoundPool()
        setListeners()

        wireWidgets()

        val song = listOf(Note(song.json))

//        val song = listOf(Note(300, "G"), Note(300, "D"),Note(300, "C"),
//            Note(300, "D"), Note(300, "E"), Note(300, "F"), Note(300, "A"),
//            Note(300, "D"), Note(300, "E"), )



        playSong.setOnClickListener {
            GlobalScope.launch {
                playSong(song)
            }
        }
    }

    private fun wireWidgets() {
        playSong = findViewById(R.id.button_main_playSong)
        playSong.setBackgroundColor(Color.BLUE)
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        binding.buttonMainA.setOnClickListener(soundBoardListener)
        binding.buttonMainBb.setOnClickListener(soundBoardListener)
        binding.buttonMainB.setOnClickListener(soundBoardListener)
        binding.buttonMainC.setOnClickListener(soundBoardListener)
        binding.buttonMainCs.setOnClickListener(soundBoardListener)
        binding.buttonMainD.setOnClickListener(soundBoardListener)
        binding.buttonMainDs.setOnClickListener(soundBoardListener)
        binding.buttonMainE.setOnClickListener(soundBoardListener)
        binding.buttonMainF.setOnClickListener(soundBoardListener)
        binding.buttonMainFs.setOnClickListener(soundBoardListener)
        binding.buttonMainG.setOnClickListener(soundBoardListener)
        binding.buttonMainGs.setOnClickListener(soundBoardListener)
        binding.buttonMainPlaySong.setOnClickListener(soundBoardListener)
    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote = soundPool.load(this, R.raw.scalea, 1)
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        bNote = soundPool.load(this, R.raw.scaleb, 1)
        cNote =  soundPool.load(this, R.raw.scalec, 1)
        csNote = soundPool.load(this, R.raw.scalecs, 1)
        dNote = soundPool.load(this, R.raw.scaled, 1)
        dsNote = soundPool.load(this, R.raw.scaleds, 1)
        eNote = soundPool.load(this, R.raw.scalee, 1)
        fNote = soundPool.load(this, R.raw.scalef, 1)
        fsNote = soundPool.load(this, R.raw.scalefs, 1)
        gNote = soundPool.load(this, R.raw.scaleg, 1)
        gsNote = soundPool.load(this, R.raw.scalegs, 1)
        //playSong = soundPool.playSong()

        //Maps use key-value pairs (just like the Bundle)
        noteMap.put("A", aNote)
        //Kotlin lets you use array-like assignments
        noteMap["Bb"] = bNote
        noteMap.put("C", cNote)
        noteMap.put("Cs", csNote)
        noteMap.put("D", dNote)
        noteMap.put("Ds", dsNote)
        noteMap.put("E", eNote)
        noteMap.put("F", fNote)
        noteMap.put("Fs", fsNote)
        noteMap.put("G", gNote)
        noteMap.put("Gs", gsNote)
    }

    private fun playNote(note: String) {
        // ?: is the elvis operator. lets you provide a default value if the value is null
        playNote(noteMap[note] ?: 0)

    }

    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    private suspend fun playSong(song: List<Note>) {
        //loop through a list of notes
        for(note in song) {
            playNote(note.note)
            delay(note.duration)
            delay(note.duration)

        }
          //play the note
          //to play the note, you need the corresponding soundPool object
        // delay for the delay

       // delay(note.delay)
    }

// blocks main thread, works but not ideal
//    private fun delay(time: Long) {
//        try {
//            Thread.sleep(time)
//        } catch(e: InterruptedException) {
//            e.printStackTrace()
//        }
//    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_a -> playNote(aNote)
                R.id.button_main_bb -> playNote(bbNote)
                R.id.button_main_b -> playNote(bNote)
                R.id.button_main_c -> playNote(cNote)
                R.id.button_main_cs -> playNote(csNote)
                R.id.button_main_d -> playNote(dNote)
                R.id.button_main_ds -> playNote(dsNote)
                R.id.button_main_e -> playNote(eNote)
                R.id.button_main_f -> playNote(fNote)
                R.id.button_main_fs -> playNote(fsNote)
                R.id.button_main_g -> playNote(gNote)
                R.id.button_main_gs -> playNote(gsNote)
            }
        }
    }
}
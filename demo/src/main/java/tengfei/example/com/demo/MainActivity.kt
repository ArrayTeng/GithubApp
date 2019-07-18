package tengfei.example.com.demo

import android.graphics.drawable.AnimatedVectorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val anim:AnimatedVectorDrawable = resources.getDrawable(R.drawable.vector_anim_log,null) as AnimatedVectorDrawable

        image_log.setImageDrawable(anim)

        anim.start()
    }
}

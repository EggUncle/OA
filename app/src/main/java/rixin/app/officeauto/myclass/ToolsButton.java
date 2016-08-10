package rixin.app.officeauto.myclass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rixin.app.officeauto.R;

/**
 * 自定义button
 */
public class ToolsButton extends LinearLayout{

    private ImageView mButtonImage;
    private TextView mButtonText;

    public ToolsButton(Context context) {
        super(context,null);
    }

    public ToolsButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tools_button, this, true);
        this.mButtonImage = (ImageView) findViewById(R.id.image);
        this.mButtonText = (TextView) findViewById(R.id.text);
        this.setClickable(true);
        this.setFocusable(true);
    }

    /**
     *
     * @param resourceId 自定义botton的图片id
     */
    public void setImage(int resourceId) {
        this.mButtonImage.setImageResource(resourceId);
    }

    /**
     *
     * @param text 自定义button的text
     */
    public void setText(String text) {
        this.mButtonText.setText(text);
    }

}

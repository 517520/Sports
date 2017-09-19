package com.example.user.sports.main.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.BuildConfig;
import com.example.user.sports.R;
import com.example.user.sports.ui.CircleImageView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.PictureCutUtil;
import com.example.user.sports.utils.PopUtil;

import java.io.File;

/**
 * Author : yufeng.cao
 * Time : 2017.09.5 14:14
 * Description : chose your headphoto and input your nickname;
 */

public class HeadActivity extends BaseActivity implements View.OnClickListener{

    private View view;
    private CircleImageView circleImageView;
    private EditText mNickNameEt;
    private Button mCompleteBtn;

    private String phone;

    private PictureCutUtil pictureCutUtil;
    private File file;
    private LinearLayout ll_popup;
    private PopUtil pop;
    private String filename = System.currentTimeMillis() + ".png";
    public final static int CONSULT_DOC_PICTURE = 1000;
    public final static int CONSULT_DOC_CAMERA = 1001;
    public final static int CONSULT_DOC_CUTTING = 1002;
    private Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setallowFullScreen(true);
        view = getLayoutInflater().inflate(R.layout.activity_head, null);
        setContentView(view);

        Intent intent = getIntent();
        Bundle map = intent.getExtras();
        phone = map.getString("phone");

        initView();
        initPop();
    }

    private void initView() {
        pictureCutUtil = new PictureCutUtil(this);

        circleImageView = (CircleImageView) findViewById(R.id.photo_head_civ);
        mNickNameEt = (EditText) findViewById(R.id.nickname_head_et);
        mCompleteBtn = (Button) findViewById(R.id.complete_head_btn);

        circleImageView.setOnClickListener(this);
        mCompleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_head_civ:
                pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.complete_head_btn:
                IntentUtils.turnTo(HeadActivity.this, LoginActivity.class, true);
                break;
            default:

                break;
        }

    }

    private void initPop() {
        pop = new PopUtil(HeadActivity.this, R.layout.item_popupwindows, false);
        ll_popup = pop.getLl_popup();

        TextView bt1 = (TextView) ll_popup.findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) ll_popup.findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) ll_popup.findViewById(R.id.item_popupwindows_cancel);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                file = new File(Environment.getExternalStorageDirectory(), filename);
                outputFileUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, CONSULT_DOC_CAMERA);

                overridePendingTransition(R.anim.translate_bottom_in, R.anim.translate_nomal);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, CONSULT_DOC_PICTURE);

                overridePendingTransition(R.anim.translate_bottom_in, R.anim.translate_nomal);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CONSULT_DOC_PICTURE:
                if (data != null) {
                    try {
                        startPhotoZoom2(data.getData());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case CONSULT_DOC_CAMERA:
                startPhotoZoom1(outputFileUri);
                break;

            case CONSULT_DOC_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom1(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        Uri outPutUri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        intent.putExtra("noFaceDetection", false);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CONSULT_DOC_CUTTING);
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom2(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CONSULT_DOC_CUTTING);
    }

    private void setPicToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            circleImageView.setImageBitmap(photo);

            File imageFile = pictureCutUtil.cutPictureQuality(photo, "headImage");
            Toast.makeText(this, imageFile.getName(), Toast.LENGTH_LONG).show();
        }
    }

}

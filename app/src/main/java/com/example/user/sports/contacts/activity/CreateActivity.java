package com.example.user.sports.contacts.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.sports.BaseActivity;
import com.example.user.sports.BuildConfig;
import com.example.user.sports.R;
import com.example.user.sports.main.activity.HeadActivity;
import com.example.user.sports.ui.AppHeadView;
import com.example.user.sports.utils.IntentUtils;
import com.example.user.sports.utils.PictureCutUtil;
import com.example.user.sports.utils.PopUtil;

import java.io.File;

/**
 * Author : yufeng.cao
 * Date : 2017.09.06 11:44
 * Description :
 */
public class CreateActivity extends BaseActivity implements View.OnClickListener{
    private View view;
    private AppHeadView headView;
    private ImageView mHeadIv, mCameraIv;
    private EditText mNameEt, mDetailEt;
    private Button mCreateBtn;
    private TextView mLocationTv;

    //头像
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
        view = getLayoutInflater().inflate(R.layout.activity_create, null);
        setContentView(view);
        setallowFullScreen(true);

        initHeadView();
        initView();
        initPop();
    }

    private void initHeadView() {
        headView = (AppHeadView) findViewById(R.id.headview);
        headView.setVisibility(View.VISIBLE, View.GONE, View.GONE, View.GONE);
        headView.setTitle("新建群组");
        headView.setOnClickListenerBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        pictureCutUtil = new PictureCutUtil(this);
        mHeadIv = (ImageView) findViewById(R.id.head_create_iv);
        mNameEt = (EditText) findViewById(R.id.name_create_et);
        mDetailEt = (EditText) findViewById(R.id.detail_create_et);
        mCreateBtn = (Button) findViewById(R.id.create_btn);
        mLocationTv = (TextView) findViewById(R.id.location_create_tv);
        mCameraIv = (ImageView) findViewById(R.id.camera_create_iv);

        mHeadIv.setOnClickListener(this);
        mCreateBtn.setOnClickListener(this);
        mLocationTv.setOnClickListener(this);
    }

    private void initPop() {
        pop = new PopUtil(CreateActivity.this, R.layout.item_popupwindows, false);
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
            case 1:
                mLocationTv.setText(data.getStringExtra("location"));
                mLocationTv.setTextColor(getResources().getColor(R.color.text_blue));
                break;
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
            mHeadIv.setImageBitmap(photo);
            mCameraIv.setVisibility(View.GONE);

            File imageFile = pictureCutUtil.cutPictureQuality(photo, "headImage");
            Toast.makeText(this, imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_create_iv:
                pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.create_btn:

                break;
            case R.id.location_create_tv:
                Intent intent = new Intent(CreateActivity.this, LocationActivity.class);
                startActivityForResult(intent,1);
                break;
            default:
                break;
        }
    }
}

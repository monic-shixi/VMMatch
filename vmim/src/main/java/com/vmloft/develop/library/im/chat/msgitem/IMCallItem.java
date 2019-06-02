package com.vmloft.develop.library.im.chat.msgitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.vmloft.develop.library.im.R;
import com.vmloft.develop.library.im.call.IMCallManager;
import com.vmloft.develop.library.im.chat.IMChatAdapter;
import com.vmloft.develop.library.im.common.IMConstants;

/**
 * Create by lzan13 on 2019/5/23 22:17
 *
 * 实现文本消息展示
 */
public class IMCallItem extends IMNotifyItem {

    private ImageView mIconView;

    public IMCallItem(Context context, IMChatAdapter adapter, int type) {
        super(context, adapter, type);
    }

    @Override
    protected View layoutView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.im_chat_item_notify_call, null);
        mIconView = view.findViewById(R.id.im_chat_msg_call_icon_iv);
        mContentView = view.findViewById(R.id.im_chat_msg_content_tv);
        return view;
    }

    @Override
    public void onBind(int position, EMMessage message) {
        super.onBind(position, message);

        EMTextMessageBody body = (EMTextMessageBody) message.getBody();
        boolean isVideo = message.getBooleanAttribute(IMConstants.IM_CHAT_MSG_EXT_TYPE_VIDEO_CALL, false);
        if (isVideo) {
            mIconView.setImageResource(R.drawable.im_ic_video);
        } else {
            mIconView.setImageResource(R.drawable.im_ic_call);
        }
        mContentView.setText(body.getMessage());
    }

    @Override
    public void onClick() {
        boolean isVideo = mMessage.getBooleanAttribute(IMConstants.IM_CHAT_MSG_EXT_TYPE_VIDEO_CALL, false);
        int type = isVideo ? IMCallManager.CallType.VIDEO : IMCallManager.CallType.VOICE;
        IMCallManager.getInstance().startCall(mMessage.conversationId(), type);
    }
}

package com.example.contactsui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context mContext;
    private List<Contact> ContactList;
    public ContactAdapter(Context mContext, List ContactList) {
        this.mContext = mContext;
        this.ContactList = ContactList;
    }

    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_obj, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder holder, int position) {
        Contact contact = ContactList.get(position);
        holder.id = contact.getId() ;
        holder.nom.setText(contact.getName());
        holder.phone.setText(contact.getPhoneNumber());
        try{

            byte[] recordImage = contact.getImage();
        //    byte [] outImage = contact.getImage();
            if(recordImage!=null) {

                Bitmap bitmap = BitmapFactory.decodeByteArray(contact.getImage(), 0, contact.getImage().length);
                holder.img_user.setImageBitmap(bitmap);

         /*       ///////////////Convertion de Byte[] to Bitmap
                ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                ///////////////Fin de Convertion de Byte[] to Bitmap
                holder.img_user.setImageBitmap(theImage);*/
            }
        }catch (Exception ex) {
            Toast.makeText(mContext,"class ContactAdapter EXception : "+ex,Toast.LENGTH_SHORT).show();

        }






        holder.deleteBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    DataBaseHandler db = new DataBaseHandler(mContext);
                    db.deleteContact(contact);
                    ContactList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(mContext,"  holder id = " +holder.id+" Contact Supprimé :( ",Toast.LENGTH_SHORT).show();
                }catch (Exception ex)
                {
                    Toast.makeText(mContext,ex.getMessage().toString()+" OR wait a momment ",Toast.LENGTH_SHORT).show();

                }

            }
        });
       holder.ModiBtn.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
            DataBaseHandler db = new DataBaseHandler(mContext);
            db.selectContact(contact);
            holder.id = contact.getId() ;
            Toast.makeText(mContext,holder.id+" Worked ",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext,MofidierActivity.class);
            intent.putExtra("ID", holder.id);
            intent.putExtra("nom",holder.nom.getText());
            intent.putExtra("telRes",holder.phone.getText());
            mContext.startActivity(intent);

        }});
     holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {
             Intent intent = new Intent(Intent.ACTION_DIAL);
             intent.setData(Uri.parse("tel:"+holder.phone.getText()));
             v.getContext().startActivity(intent);
             Toast.makeText(mContext,holder.phone.getText(),Toast.LENGTH_SHORT).show();
             return false;
         }
     });



    }
    @Override
    public int getItemCount() {
        return ContactList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom, phone;
        int id ;
        public ImageView img_user ;
        public ImageButton deleteBnt , ModiBtn ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.txt_nom);
            phone =  itemView.findViewById(R.id.txt_phone);
            deleteBnt = itemView.findViewById(R.id.deletebnt);
            ModiBtn = itemView.findViewById(R.id.ModfierBnt);
            img_user = itemView.findViewById(R.id.row_image);

        }
    }
}

package baselib.base2;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BindingViewHolder<T, BINDING extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected BINDING binding;

    public BindingViewHolder(@NonNull BINDING binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public abstract void bind(T data);
}

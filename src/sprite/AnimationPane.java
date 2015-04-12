package sprite;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.*;

public class AnimationPane extends AnchorPane implements List<Sprite> {

    public void setBackgroundImage(String url) {
        setBackground(new Background(new BackgroundImage(new Image(url), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

    }

    @Override
    public int size() {
        return getChildren().size();
    }

    @Override
    public boolean isEmpty() {
        return getChildren().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getChildren().contains(o);
    }

    @Override
    public Iterator<Sprite> iterator() {
        Iterator<Sprite> i = new Iterator<Sprite>() {

            @Override
            public boolean hasNext() {
                return getChildren().iterator().hasNext();
            }

            @Override
            public Sprite next() {
                return (Sprite)getChildren().iterator().next();
            }
        };
        return i;
    }

    @Override
    public Object[] toArray() {
        return getChildren().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getChildren().toArray(a);
    }


    @Override
    public boolean add(Sprite sprite) {
        return getChildren().add((Node)sprite);
    }

    @Override
    public boolean remove(Object o) {
        return getChildren().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getChildren().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Sprite> c) {
        return getChildren().addAll((Collection<? extends Node>)c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Sprite> c) {
        return getChildren().addAll(index, (Collection<? extends Node>)c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getChildren().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getChildren().retainAll(c);
    }

    @Override
    public void clear() {
        getChildren().clear();
    }

    @Override
    public Sprite get(int index) {
        return (Sprite)getChildren().get(index);
    }

    @Override
    public Sprite set(int index, Sprite element) {
        return (Sprite)getChildren().set(index, (Node)element);
    }

    @Override
    public void add(int index, Sprite element) {

    }

    @Override
    public Sprite remove(int index) {
        return (Sprite)getChildren().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return getChildren().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getChildren().lastIndexOf(o);
    }

    @Override
    public ListIterator<Sprite> listIterator() {
        ListIterator<Sprite> li = new ListIterator<Sprite>() {

            @Override
            public boolean hasNext() {
                return getChildren().listIterator().hasNext();
            }

            @Override
            public Sprite next() {
                return (Sprite)getChildren().listIterator().next();
            }

            @Override
            public boolean hasPrevious() {
                return getChildren().listIterator().hasPrevious();
            }

            @Override
            public Sprite previous() {
                return (Sprite)getChildren().listIterator().previous();
            }

            @Override
            public int nextIndex() {
                return getChildren().listIterator().nextIndex();
            }

            @Override
            public int previousIndex() {
                return getChildren().listIterator().previousIndex();
            }

            @Override
            public void remove() {
                getChildren().listIterator().remove();
            }

            @Override
            public void set(Sprite sprite) {
                getChildren().listIterator().set(sprite);
            }

            @Override
            public void add(Sprite sprite) {
                getChildren().listIterator().add(sprite);
            }
        };

        return li;
    }

    @Override
    public ListIterator<Sprite> listIterator(int index) {
        ListIterator<Sprite> li = new ListIterator<Sprite>() {

            @Override
            public boolean hasNext() {
                return getChildren().listIterator(index).hasNext();
            }

            @Override
            public Sprite next() {
                return (Sprite)getChildren().listIterator(index).next();
            }

            @Override
            public boolean hasPrevious() {
                return getChildren().listIterator(index).hasPrevious();
            }

            @Override
            public Sprite previous() {
                return (Sprite)getChildren().listIterator(index).previous();
            }

            @Override
            public int nextIndex() {
                return getChildren().listIterator(index).nextIndex();
            }

            @Override
            public int previousIndex() {
                return getChildren().listIterator(index).previousIndex();
            }

            @Override
            public void remove() {
                getChildren().listIterator(index).remove();
            }

            @Override
            public void set(Sprite sprite) {
                getChildren().listIterator(index).set(sprite);
            }

            @Override
            public void add(Sprite sprite) {
                getChildren().listIterator(index).add(sprite);
            }
        };

        return li;
    }

    @Override
    public List<Sprite> subList(int fromIndex, int toIndex) {
        ArrayList<Sprite> a = new ArrayList<>(toIndex - fromIndex + 1);

        List<Node> l = getChildren().subList(fromIndex, toIndex);
        for (Node n : l) {
            a.add((Sprite)n);
        }

        return a;
    }
}

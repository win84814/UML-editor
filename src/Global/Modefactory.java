package Global;

import Modes.AssociationLineMode;
import Modes.ClassboxMode;
import Modes.CompositionLineMode;
import Modes.GeneralizationLineMode;
import Modes.Mode;
import Modes.SelectMode;
import Modes.UsecaseMode;
public class ModeFactory {
	public static Mode create(int no, GUI.Canvas canvas){
		switch (no) {
			case GlobalVar.SELECT: return new SelectMode(canvas);
			case GlobalVar.ASSOCIATION: return new AssociationLineMode(canvas);
			case GlobalVar.GENERALIZATION: return new GeneralizationLineMode(canvas);
			case GlobalVar.COMPOSITION: return new CompositionLineMode(canvas);
			case GlobalVar.CLASS: return new ClassboxMode(canvas);
			case GlobalVar.USECASE: return new UsecaseMode(canvas);
			default: return null;
		}
	}
}

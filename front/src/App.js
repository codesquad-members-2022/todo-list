import styles from "./App.module.css";
import Columns from "./components/Content/Columns/Columns";
import Content from "./components/Content/Content";
import Header from "./components/Header/Header";
import SideContent from "./components/SideContent/SideContent";
import peact from "./core/peact";
import columnApi from "./service/columnApi";
import logApi from "./service/logApi";
import todoApi from "./service/todoApi";

const App = () => {
  const [todos, setTodos] = peact.useState([]);
  const [columns, setColumns] = peact.useState([]);
  const [todoLogs, setTodoLogs] = peact.useState([]);
  const [renderFlag, setRenderFlag] = peact.useState(false);

  const handleRenderFlag = () => {
    setRenderFlag(!renderFlag);
  };

  peact.useEffect(() => {
    const fetchTodos = async () => {
      const newTodos = await todoApi.getTodos();
      setTodos(newTodos);
    };
    const fetchColumns = async () => {
      const newColumns = await columnApi.getColumns();
      setColumns(newColumns);
    };
    const fetchTodoLogs = async () => {
      const newTodoLogs = await logApi.getTodoLogs();
      setTodoLogs(newTodoLogs);
    };
    fetchTodos();
    fetchColumns();
    fetchTodoLogs();
  }, [renderFlag]);

  const $columns = Columns({ columns, todos, handleRenderFlag });

  const $todoListArea = peact.createElement({
    tag: "div",
    className: styles.todolistArea,
    child: [Header(), Content({ content: $columns })],
  });

  return peact.createElement({
    tag: "div",
    className: styles.wrap,
    child: [$todoListArea, SideContent({ todoLogs, columns })],
  });
};

export default App;

import styles from "./App.module.css";
import Columns from "./components/Content/Columns/Columns";
import Content from "./components/Content/Content";
import Header from "./components/Header/Header";
import Modal from "./components/Modal/Modal";
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
  const [selectedTodoId, setSelectedTodoId] = peact.useState(null);
  const [isModalVisible, setIsModalVisible] = peact.useState(false);

  const modalRef = peact.useRef();

  const handleRenderFlag = () => {
    setRenderFlag(!renderFlag);
  };

  const handleModalVisibility = () => {
    setIsModalVisible(!isModalVisible);
  };

  const handleSelectedTodoId = (todoId) => {
    setSelectedTodoId(todoId);
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

  const modalHandlers = {
    handleRenderFlag,
    handleSelectedTodoId,
    handleModalVisibility,
  };

  const $modal = Modal({
    handlers: modalHandlers,
    ref: modalRef,
    isModalVisible,
    selectedTodoId,
  });

  const columnsHandlers = {
    handleRenderFlag,
    handleSelectedTodoId,
    handleModalVisibility,
  };

  const $columns = Columns({
    columns,
    todos,
    handlers: columnsHandlers,
  });

  const $todoListArea = peact.createElement({
    tag: "div",
    className: styles.todolistArea,
    child: [Header(), Content({ content: $columns })],
  });

  return peact.createElement({
    tag: "div",
    className: styles.wrap,
    child: [$todoListArea, SideContent({ todoLogs, columns }), $modal],
  });
};

export default App;

from flask import *
from contextlib import closing
import MySQLdb

#conf
DATABASE = ""
DEBUG = True
SECRET_KEY='development key'

app = Flask(__name__)
#if we move conf to other file, we can use from_envvar('filename', silent) instead of from_object()
app.config.from_object(__name__) 

def connect_db():
	return MySQLdb.connect(user="root", passwd='564288', host='127.0.0.1', db='todo')
	
def init_db(filename):
	with app.app_context():
		db=get_db()
		file = open(filename,'r')
		sql = " ".join(file.readlines())
		cursor = db.cursor()
		cursor.execute(sql)
		db.commit()

def get_db():
	"""opens the connection to database if not exists"""
	if not hasattr(g, 'MySQLdb'):
		g.MySQLdb=connect_db()
	return g.MySQLdb
	
@app.teardown_appcontext
def close_db(error):
	"""Closes the database connections"""
	if hasattr(g, 'MySQLdb'):
		g.MySQLdb.close()
	
@app.route('/')
def home_page():
	return render_template('home_page.html')
	
@app.route('/create', methods=['GET'])
def accept_tasks():
	#db = get_db()
	#cursor = db.cursor()
	#cursor.execute("select * from tasks order by id desc")
	#tasks = [dict(title=row[0], description=row[1]) for row in cursor.fetchall()]
	return render_template('add_tasks.html')
	
@app.route('/add', methods=['POST'])
def add_tasks():
	if((request.form['title'] and request.form['description'])==""):
		flash("Empty task cannot be created")
	else:
		db=get_db()
		cursor = db.cursor()
		cursor.execute('insert into tasks(title, description) values(%s, %s)', 
					[request.form['title'], request.form['description']])
		db.commit()
		flash("New task was successfully saved")
	return redirect(url_for('accept_tasks'))

@app.route('/disp', methods=['GET'])	
def display_tasks():
	db = get_db()
	cursor = db.cursor()
	cursor.execute("select title, description from tasks order by id desc")
	tasks = [dict(title=row[0], description=row[1]) for row in cursor.fetchall()]
	print tasks
	return render_template('display_tasks.html', tasks=tasks)
	
if __name__ == '__main__':
	init_db("schema.sql")
	app.run()
[x]Criar pacote data
    [x]Dentro do pacote, criar entidade Movie com properties
    [x]- description: String
    [x]- image: String
    [x]- title: String

[x]Ajustar main xml (criar se necessário):
    [x]Criar Layout com dois botões: "Load Movies" "Erase Movies"
    [X]- Criar progress bar gone abaixo dos botões
    [x]- Adicionar uma recycler view abaixo da progress bar

[x]Criar pacote ui e dentro deste criar pacote main.

[x]MainViewModel:
    [x]- Criar MutableLiveDatas:
    [x]showLoadingLiveData (tipo Unit)
    [x]hideLoadingLiveData (tipo Unit)
    [x]navigateToDetailsLiveData (tipo Movie)
    [x]moviesLiveData (Tipo List<Movie>)
    [x]- Criar função setIsLoading(loading: Boolean)

//Parte 2

Dentro da MainActivity
    [x]- Instanciar MainViewModel
    [x]- Criar função observeViewModel:
    [x]Adicionar observe para showLoadingLivedata: no onChanged setar progress bar para visible
    [x]Adicionar observe para hideLoadingLivedata: no onChanged setar progress bar para gone
    [x]Adicionar observe para showErrorMessageLiveData: no onChanged setar
    [x]Toast.makeText(activity, message, Toast.LENGTH_SHORT)

//Parte 3
[x]Dentro do pacote main
    [x]Criar MovieAdapter
    [x]Criar item_movie

//Parte 4
[x]Crie pacote repository

Criar interface:
interface MovieDataSource {

interface LoadMoviesCallback {
void onMoviesLoaded(List<Movie> movies);
void onDataNotAvailable();
void onError();
}

void getMovies(LoadMoviesCallback callback);
}

[x]Criar movie api
    class MovieResponse {
        @Expose
        @SerializedName("movies")
        var movies: List<Movie>? = null
    }

[x]Criar MovieResponse
    class MovieResponse {
        @Expose
        @SerializedName("movies")
        var movies: List<Movie>? = null
    }

[z]Criar MoviesRepository
        private lateinit var mMovieApi: MovieApi

        init {
        createApi()
        }

        private fun createApi() {
           val mRetrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
            mMovieApi = mRetrofit.create(MovieApi::class.java)
        }

        override suspend fun getMovies(callback: MovieDataSource.LoadMoviesCallback) = withContext(Dispatchers.IO) {
            //TODO Implementar
        }

        companion object {
            var URL = "https://demo2458961.mockable.io
        }
    }
    
    
   // HANDSON 6
   
   Movie details
   
   1. Criar tela de Details 
   [x]- image
   [x]- titulo
   [x]- description
   [x]-"Download Movie" button
   
   worker
   
   1. Criar dentro de pacote "data" um novo pacote "manager"
   2. Criar DownloadWorker que extende de worker para realizar download de filme.
   3. Na activity de Movie Details, no clique de "Download Movie" iniciar DownloadWorker. 
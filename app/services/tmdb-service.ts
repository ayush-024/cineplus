import { Http } from '@nativescript/core';

export interface Movie {
  id: number;
  title: string;
  overview: string;
  poster_path: string;
  backdrop_path: string;
  release_date: string;
  vote_average: number;
  genre_ids: number[];
}

export interface TVShow {
  id: number;
  name: string;
  overview: string;
  poster_path: string;
  backdrop_path: string;
  first_air_date: string;
  vote_average: number;
  genre_ids: number[];
}

export interface Genre {
  id: number;
  name: string;
}

export interface SearchResult {
  id: number;
  title?: string;
  name?: string;
  overview: string;
  poster_path: string;
  backdrop_path: string;
  release_date?: string;
  first_air_date?: string;
  vote_average: number;
  media_type: string;
}

export class TMDBService {
  private static instance: TMDBService;
  private readonly API_KEY = 'b23ec55eabe9c0cb170d9571617e1ebe'; // Replace with your actual API key
  private readonly BASE_URL = 'https://api.themoviedb.org/3';
  private readonly IMAGE_BASE_URL = 'https://image.tmdb.org/t/p/w500';

  private constructor() {}

  static getInstance(): TMDBService {
    if (!TMDBService.instance) {
      TMDBService.instance = new TMDBService();
    }
    return TMDBService.instance;
  }

  getImageUrl(path: string): string {
    return path ? `${this.IMAGE_BASE_URL}${path}` : '';
  }

  async getPopularMovies(): Promise<Movie[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/movie/popular?api_key=${this.API_KEY}`);
      return response.results || [];
    } catch (error) {
      console.error('Error fetching popular movies:', error);
      return [];
    }
  }

  async getTopRatedMovies(): Promise<Movie[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/movie/top_rated?api_key=${this.API_KEY}`);
      return response.results || [];
    } catch (error) {
      console.error('Error fetching top rated movies:', error);
      return [];
    }
  }

  async getUpcomingMovies(): Promise<Movie[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/movie/upcoming?api_key=${this.API_KEY}`);
      return response.results || [];
    } catch (error) {
      console.error('Error fetching upcoming movies:', error);
      return [];
    }
  }

  async getPopularTVShows(): Promise<TVShow[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/tv/popular?api_key=${this.API_KEY}`);
      return response.results || [];
    } catch (error) {
      console.error('Error fetching popular TV shows:', error);
      return [];
    }
  }

  async getTopRatedTVShows(): Promise<TVShow[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/tv/top_rated?api_key=${this.API_KEY}`);
      return response.results || [];
    } catch (error) {
      console.error('Error fetching top rated TV shows:', error);
      return [];
    }
  }

  async getOnTheAirTVShows(): Promise<TVShow[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/tv/on_the_air?api_key=${this.API_KEY}`);
      return response.results || [];
    } catch (error) {
      console.error('Error fetching on the air TV shows:', error);
      return [];
    }
  }

  async getMovieDetails(id: number): Promise<Movie> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/movie/${id}?api_key=${this.API_KEY}`);
      return response;
    } catch (error) {
      console.error('Error fetching movie details:', error);
      throw error;
    }
  }

  async getTVShowDetails(id: number): Promise<TVShow> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/tv/${id}?api_key=${this.API_KEY}`);
      return response;
    } catch (error) {
      console.error('Error fetching TV show details:', error);
      throw error;
    }
  }

  async searchMulti(query: string): Promise<SearchResult[]> {
    try {
      const response = await Http.getJSON(`${this.BASE_URL}/search/multi?api_key=${this.API_KEY}&query=${encodeURIComponent(query)}`);
      return response.results || [];
    } catch (error) {
      console.error('Error searching:', error);
      return [];
    }
  }

  async getGenres(): Promise<Genre[]> {
    try {
      const movieGenres = await Http.getJSON(`${this.BASE_URL}/genre/movie/list?api_key=${this.API_KEY}`);
      const tvGenres = await Http.getJSON(`${this.BASE_URL}/genre/tv/list?api_key=${this.API_KEY}`);
      
      const allGenres = [...movieGenres.genres, ...tvGenres.genres];
      // Remove duplicates
      const uniqueGenres = allGenres.filter((genre, index, self) => 
        index === self.findIndex(g => g.id === genre.id)
      );
      
      return uniqueGenres;
    } catch (error) {
      console.error('Error fetching genres:', error);
      return [];
    }
  }

  // Mock method for getting streaming URLs - replace with your actual streaming service
  async getStreamingUrl(id: number, type: 'movie' | 'tv'): Promise<string> {
    // This is a placeholder - replace with your actual streaming service integration
    return `https://your-streaming-service.com/stream/${type}/${id}`;
  }
}
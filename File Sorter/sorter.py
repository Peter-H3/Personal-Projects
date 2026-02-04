from pathlib import Path
import shutil

# Directory that will be organised by file extension
SOURCE_DIR = Path.home() / "Downloads" / "FileSorter"

# File type mappings
FILE_TYPES = {
    "Images": [".jpg", ".jpeg", ".png", ".gif", ".webp"],
    "Documents": [".pdf", ".docx", ".txt", ".xlsx", ".pptx"],
    "Audio": [".mp3", ".wav", ".flac"],
    "Videos": [".mp4", ".mov", ".avi"],
    "Archives": [".zip", ".rar", ".7z"],
}

def get_folder_for_extension(ext):
    for folder, extensions in FILE_TYPES.items():
        if ext in extensions:
            return folder
    return "Other"

def organise_files():
    for item in SOURCE_DIR.iterdir():
        if item.is_file():
            folder_name = get_folder_for_extension(item.suffix.lower())
            destination = SOURCE_DIR / folder_name
            destination.mkdir(exist_ok=True)

            shutil.move(str(item), destination / item.name)
            print(f"Moved {item.name} → {folder_name}/")

if __name__ == "__main__":
    organise_files()
